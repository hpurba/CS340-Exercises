import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.*;
import com.amazonaws.services.dynamodbv2.model.*;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;

import java.util.*;


public class VisitsDao {

	private static final String TableName = "visits";
	private static final String IndexName = "visits-index";

	private static final String VisitorAttr = "visitor";
	private static final String LocationAttr = "location";
	private static final String VisitCountAttr = "visit_count";

	// DynamoDB client
	private static AmazonDynamoDB amazonDynamoDB = AmazonDynamoDBClientBuilder
														.standard()
														.withRegion("us-west-2")
														.build();
	private static DynamoDB dynamoDB = new DynamoDB(amazonDynamoDB);

	private static boolean isNonEmptyString(String value) {
		return (value != null && value.length() > 0);
	}

	/**
	 * Create the "visits" table and the "visits-index" global index
	 *
	 * @throws DataAccessException
	 */
	public void createTable() throws DataAccessException {
		try {
			// Attribute definitions
			ArrayList<AttributeDefinition> tableAttributeDefinitions = new ArrayList<>();

			tableAttributeDefinitions.add(new AttributeDefinition()
					.withAttributeName("visitor")
					.withAttributeType("S"));
			tableAttributeDefinitions.add(new AttributeDefinition()
					.withAttributeName("location")
					.withAttributeType("S"));

			// Table key schema
			ArrayList<KeySchemaElement> tableKeySchema = new ArrayList<>();
			tableKeySchema.add(new KeySchemaElement()
					.withAttributeName("visitor")
					.withKeyType(KeyType.HASH));  //Partition key
			tableKeySchema.add(new KeySchemaElement()
					.withAttributeName("location")
					.withKeyType(KeyType.RANGE));  //Sort key

			// Index
			GlobalSecondaryIndex index = new GlobalSecondaryIndex()
					.withIndexName("visits-index")
					.withProvisionedThroughput(new ProvisionedThroughput()
							.withReadCapacityUnits((long) 1)
							.withWriteCapacityUnits((long) 1))
					.withProjection(new Projection().withProjectionType(ProjectionType.ALL));

			ArrayList<KeySchemaElement> indexKeySchema = new ArrayList<>();

			indexKeySchema.add(new KeySchemaElement()
					.withAttributeName("location")
					.withKeyType(KeyType.HASH));  //Partition key
			indexKeySchema.add(new KeySchemaElement()
					.withAttributeName("visitor")
					.withKeyType(KeyType.RANGE));  //Sort key

			index.setKeySchema(indexKeySchema);

			CreateTableRequest createTableRequest = new CreateTableRequest()
					.withTableName("visits")
					.withProvisionedThroughput(new ProvisionedThroughput()
							.withReadCapacityUnits((long) 1)
							.withWriteCapacityUnits((long) 1))
					.withAttributeDefinitions(tableAttributeDefinitions)
					.withKeySchema(tableKeySchema)
					.withGlobalSecondaryIndexes(index);

			Table table = dynamoDB.createTable(createTableRequest);
			table.waitForActive();
		}
		catch (Exception e) {
			throw new DataAccessException(e);
		}
	}

	/**
	 * Delete the "visits" table and the "visits-index" global index
	 *
	 * @throws DataAccessException
	 */
	public void deleteTable() throws DataAccessException {
		try {
			Table table = dynamoDB.getTable(TableName);
			if (table != null) {
				table.delete();
				table.waitForDelete();
			}
		}
		catch (Exception e) {
			throw new DataAccessException(e);
		}
	}

	/**
	 * Retrieve the number of times visitor has visited location
	 *
	 * @param visitor
	 * @param location
	 * @return
	 */
	public int getVisitCount(String visitor, String location) {
		Table table = dynamoDB.getTable(TableName);

		Item item = table.getItem(VisitorAttr, visitor,	LocationAttr, location);
		if (item == null) {
			return 0;
		}
		else {
			return item.getInt(VisitCountAttr);
		}
	}

	/**
	 * Increment the number of times visitor has visited location
	 *
	 * @param visitor
	 * @param location
	 */
	public void recordVisit(String visitor, String location) {
		Table table = dynamoDB.getTable(TableName);

		try {
			// Increment the visit count.
			// If visitor has never visited location, this will throw an exception.

			Map<String, String> attrNames = new HashMap<String, String>();
			attrNames.put("#viscnt", VisitCountAttr);

			Map<String, Object> attrValues = new HashMap<String, Object>();
			attrValues.put(":val", 1);

			table.updateItem(VisitorAttr, visitor, LocationAttr, location,
					"set #viscnt = #viscnt + :val", attrNames, attrValues);
		}
		catch (Exception e) {
			// Record first visit of visitor to location

			Item item = new Item()
					.withPrimaryKey(VisitorAttr, visitor, LocationAttr, location)
					.withNumber(VisitCountAttr, 1);

			table.putItem(item);
		}
	}

	/**
	 * Delete all visits of visitor to location
	 *
	 * @param visitor
	 * @param location
	 */
	public void deleteVisit(String visitor, String location) {
		Table table = dynamoDB.getTable(TableName);
		table.deleteItem(VisitorAttr, visitor,	LocationAttr, location);
	}

	/**
	 * Fetch the next page of locations visited by visitor
	 *
	 * @param visitor The visitor of interest
	 * @param pageSize The maximum number of locations to include in the result
	 * @param lastLocation The last location returned in the previous page of results
	 * @return The next page of locations visited by visitor
	 */
	public ResultsPage getVisitedLocations(String visitor, int pageSize, String lastLocation) {
		ResultsPage result = new ResultsPage();

		Map<String, String> attrNames = new HashMap<String, String>();
		attrNames.put("#vis", VisitorAttr);

		Map<String, AttributeValue> attrValues = new HashMap<>();
		attrValues.put(":visitor", new AttributeValue().withS(visitor));

		QueryRequest queryRequest = new QueryRequest()
				.withTableName(TableName)
				.withKeyConditionExpression("#vis = :visitor")
				.withExpressionAttributeNames(attrNames)
				.withExpressionAttributeValues(attrValues)
				.withLimit(pageSize);

		if (isNonEmptyString(lastLocation)) {
			Map<String, AttributeValue> startKey = new HashMap<>();
			startKey.put(VisitorAttr, new AttributeValue().withS(visitor));
			startKey.put(LocationAttr, new AttributeValue().withS(lastLocation));

			queryRequest = queryRequest.withExclusiveStartKey(startKey);
		}

		QueryResult queryResult = amazonDynamoDB.query(queryRequest);
		List<Map<String, AttributeValue>> items = queryResult.getItems();
		if (items != null) {
			for (Map<String, AttributeValue> item : items){
				String location = item.get(LocationAttr).getS();
				result.addValue(location);
			}
		}

		Map<String, AttributeValue> lastKey = queryResult.getLastEvaluatedKey();
		if (lastKey != null) {
			result.setLastKey(lastKey.get(LocationAttr).getS());
		}

		return result;
	}

	/**
	 * Fetch the next page of visitors who have visited location
	 *
	 * @param location The location of interest
	 * @param pageSize The maximum number of visitors to include in the result
	 * @param lastVisitor The last visitor returned in the previous page of results
	 * @return The next page of visitors who have visited location
	 */
	public ResultsPage getVisitors(String location, int pageSize, String lastVisitor) {
		ResultsPage result = new ResultsPage();

		Map<String, String> attrNames = new HashMap<String, String>();
		attrNames.put("#loc", LocationAttr);

		Map<String, AttributeValue> attrValues = new HashMap<>();
		attrValues.put(":location", new AttributeValue().withS(location));

		QueryRequest queryRequest = new QueryRequest()
				.withTableName(TableName)
				.withIndexName(IndexName)
				.withKeyConditionExpression("#loc = :location")
				.withExpressionAttributeNames(attrNames)
				.withExpressionAttributeValues(attrValues)
				.withLimit(pageSize);

		if (isNonEmptyString(lastVisitor)) {
			Map<String, AttributeValue> lastKey = new HashMap<>();
			lastKey.put(LocationAttr, new AttributeValue().withS(location));
			lastKey.put(VisitorAttr, new AttributeValue().withS(lastVisitor));

			queryRequest = queryRequest.withExclusiveStartKey(lastKey);
		}

		QueryResult queryResult = amazonDynamoDB.query(queryRequest);
		List<Map<String, AttributeValue>> items = queryResult.getItems();
		if (items != null) {
			for (Map<String, AttributeValue> item : items){
				String visitor = item.get(VisitorAttr).getS();
				result.addValue(visitor);
			}
		}

		Map<String, AttributeValue> lastKey = queryResult.getLastEvaluatedKey();
		if (lastKey != null) {
			result.setLastKey(lastKey.get(VisitorAttr).getS());
		}

		return result;
	}

}
