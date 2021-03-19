import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.*;
import com.amazonaws.services.dynamodbv2.document.spec.DeleteItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.amazonaws.services.dynamodbv2.document.utils.NameMap;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.QueryRequest;
import com.amazonaws.services.dynamodbv2.model.QueryResult;
import com.amazonaws.services.dynamodbv2.model.ReturnValue;

import java.util.*;

public class FollowsDAO {
  private static final String TableName = "follows";
  private static final String PartitionKey = "follower_handle";
  private static final String IndexName = "followee_handle";

  private static final String FolloweeNameAtr = "followee_name";
  private static final String FollowerNameAtr = "follower_name";

  // DynamoDB client
  private static AmazonDynamoDB amazonDynamoDB = AmazonDynamoDBClientBuilder
    .standard()
    .withRegion("us-west-2")
    .build();
  private static DynamoDB dynamoDB = new DynamoDB(amazonDynamoDB);

  private static Table table = dynamoDB.getTable(TableName);

  /**
   * Add and entry into the Table
   * @param entry
   */
  public void put(Entry entry) {
    // Item to add / put
    Item item = new Item()
      .withPrimaryKey(PartitionKey, entry.follower_handle)
      .withString(IndexName, entry.followee_handle)
      .withString(FolloweeNameAtr, entry.followee_name)
      .withString(FollowerNameAtr, entry.follower_name);

    try {
      System.out.println("Adding a new item...");
      // Write the item to the table
      PutItemOutcome outcome = table.putItem(item);
      System.out.println("PutItem succeeded:\n" + outcome.getPutItemResult());
    }
    catch (Exception e) {
      System.err.println("Unable to add item.");
      System.err.println(e.getMessage());
    }
  }

  /**
   * Get an Entry from the table
   *
   * @param entry
   */
  public void get(Entry entry) {

    GetItemSpec spec = new GetItemSpec().withPrimaryKey(PartitionKey, entry.follower_handle, IndexName, entry.followee_handle);

    try {
      System.out.println("Attempting to read the item...");
      Item outcome = table.getItem(spec);
      System.out.println("GetItem succeeded: " + outcome);

    }
    catch (Exception e) {
      System.err.println("Unable to read item: " + entry.follower_handle + " " + entry.followee_handle);
      System.err.println(e.getMessage());
    }
  }

  /**
   * Update an entry with a new Entry
   * @param originalEntry
   * @param newEntry
   */
  public void update(Entry originalEntry, Entry newEntry) {
    // UpdateItemSpecification
    UpdateItemSpec updateItemSpec = new UpdateItemSpec()
      .withPrimaryKey(PartitionKey, originalEntry.follower_handle, IndexName, originalEntry.followee_handle)
      .withUpdateExpression("set followee_name =:fen, follower_name=:frn")
      .withValueMap(new ValueMap()
        .withString(":fen", newEntry.followee_name)
        .withString(":frn", newEntry.follower_name))
      .withReturnValues(ReturnValue.UPDATED_NEW);
    // Attempt an update
    try {
      System.out.println("Updating the item...");
      UpdateItemOutcome outcome = table.updateItem(updateItemSpec);
      System.out.println("UpdateItem succeeded:\n" + outcome.getItem().toJSONPretty());
    }
    catch (Exception e) {
      System.err.println("Unable to update item.");
      System.err.println(e.getMessage());
    }
  }

  /**
   * Delete a given Entry
   *
   * @param entryToDelete
   */
  public void delete(Entry entryToDelete) {
    // Item to Delete Specification
    DeleteItemSpec deleteItemSpec = new DeleteItemSpec()
      .withPrimaryKey(PartitionKey, entryToDelete.follower_handle, IndexName, entryToDelete.followee_handle);
    // Delete Attempt
    try {
      System.out.println("Attempting a conditional delete...");
      table.deleteItem(deleteItemSpec);
      System.out.println("DeleteItem succeeded");
    }
    catch (Exception e) {
      System.err.println("Unable to delete item.");
      System.err.println(e.getMessage());
    }
  }

  /**
   * Fetch the next page of followee_handle by follower_handle
   * “Query” the “follows” table to return all of the users being followed by a user, sortedby “followee_handle”
   * Hint: Call QuerySpec.withScanIndexForward(true) to sort the result in ascending order
   *
   * @param followerHandle The follower of interest
   * @param pageSize The maximum number of followees to include in the result
   * @param lastFollowee Sorted by followee_handle
   * @return The next page of locations visited by visitor
   */
  public ResultsPage getFolloweeHandleOfFollower(String followerHandle, int pageSize, String lastFollowee) {
    ResultsPage result = new ResultsPage();
    Map<String, String> attrNames = new HashMap<String, String>();
    attrNames.put("#follower_handle", PartitionKey);
    Map<String, AttributeValue> attrValues = new HashMap<String, AttributeValue>();
    attrValues.put(":follower", new AttributeValue().withS(followerHandle));

    // Query Request
    QueryRequest queryRequest = new QueryRequest()
      .withTableName(TableName)
      .withKeyConditionExpression("#follower_handle = :follower")
      .withExpressionAttributeNames(attrNames)
      .withExpressionAttributeValues(attrValues)
      .withLimit(pageSize)
      .withScanIndexForward(true);

    if (isNonEmptyString(lastFollowee)) {
      Map<String, AttributeValue> startKey = new HashMap<>();
      startKey.put(PartitionKey, new AttributeValue().withS(followerHandle));
      startKey.put(IndexName, new AttributeValue().withS(lastFollowee));
      queryRequest = queryRequest.withExclusiveStartKey(startKey);
    }
    QueryResult queryResult = amazonDynamoDB.query(queryRequest);
    List<Map<String, AttributeValue>> items = queryResult.getItems();
    if (items != null) {
      for (Map<String, AttributeValue> item : items){
        String location = item.get(IndexName).getS();
        result.addValue(location);
      }
    }
    Map<String, AttributeValue> lastKey = queryResult.getLastEvaluatedKey();
    if (lastKey != null) {
      result.setLastKey(lastKey.get(IndexName).getS());
    }
    return result;
  }


  // Find followers of user given followee_handle
  // AWS Link to exampleQueryIndex
  public ResultsPage queryIndex(String followee_handle, int pageSize, String lastFollower) {
    ResultsPage result = new ResultsPage();

    Map<String, String> attrNames = new HashMap<String, String>();
    attrNames.put("#flweHndl", IndexName);

    Map<String, AttributeValue> attrValues = new HashMap<>();
    attrValues.put(":followee_handle", new AttributeValue().withS(followee_handle));

    QueryRequest queryRequest = new QueryRequest()
      .withTableName(TableName)
      .withIndexName("follows_index")
      .withKeyConditionExpression("#flweHndl = :followee_handle")
      .withExpressionAttributeNames(attrNames)
      .withExpressionAttributeValues(attrValues)
      .withLimit(pageSize)
      .withScanIndexForward(false);

    if (isNonEmptyString(lastFollower)) {
      Map<String, AttributeValue> lastKey = new HashMap<>();
      lastKey.put(FolloweeNameAtr, new AttributeValue().withS(followee_handle));
      lastKey.put(FollowerNameAtr, new AttributeValue().withS(lastFollower));

      queryRequest = queryRequest.withExclusiveStartKey(lastKey);
    }

    QueryResult queryResult = amazonDynamoDB.query(queryRequest);
    List<Map<String, AttributeValue>> items = queryResult.getItems();
    if (items != null) {
      for (Map<String, AttributeValue> item : items){
        String visitor = item.get(FollowerNameAtr).getS();
        result.addValue(visitor);
      }
    }

    Map<String, AttributeValue> lastKey = queryResult.getLastEvaluatedKey();
    if (lastKey != null) {
      result.setLastKey(lastKey.get(FollowerNameAtr).getS());
    }

    return result;
  }

  private static boolean isNonEmptyString(String value) {
    return (value != null && value.length() > 0);
  }
}
