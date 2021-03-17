import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.*;
import com.amazonaws.services.dynamodbv2.document.spec.DeleteItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.amazonaws.services.dynamodbv2.document.utils.NameMap;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.ReturnValue;

import java.util.Arrays;

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
//
  private static Table table = dynamoDB.getTable(TableName);

//  private static boolean isNonEmptyString(String value) {
//    return (value != null && value.length() > 0);
//  }




  public void put(Entry entry) {

//    Table table = dynamoDB.getTable(TableName);

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

  public void update(Entry originalEntry, Entry newEntry) {


    UpdateItemSpec updateItemSpec = new UpdateItemSpec()
      .withPrimaryKey(PartitionKey, originalEntry.follower_handle, IndexName, originalEntry.followee_handle)
      .withUpdateExpression("set followee_name =:fen, follower_name=:frn")
      .withValueMap(new ValueMap()
        .withString(":fen", newEntry.followee_name)
        .withString(":frn", newEntry.follower_name))
      .withReturnValues(ReturnValue.UPDATED_NEW);

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

  public void delete(Entry entryToDelete) {
    DeleteItemSpec deleteItemSpec = new DeleteItemSpec()
      .withPrimaryKey(PartitionKey, entryToDelete.follower_handle, IndexName, entryToDelete.followee_handle);

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


//
//  /**
//   * Retrieve the number of times visitor has visited location
//   *
//   * @param visitor
//   * @param location
//   * @return
//   */
//  public int getVisitCount(String visitor, String location) {
//    Table table = dynamoDB.getTable(TableName);
//
//    Item item = table.getItem(FolloweeNameAtr, visitor, FollowerNameAtr, location);
//    if (item == null) {
//      return 0;
//    }
//    else {
//      return item.getInt(VisitCountAttr);
//    }
//  }
//
//  /**
//   * Increment the number of times visitor has visited location
//   *
//   * @param visitor
//   * @param location
//   */
//  public void recordVisit(String visitor, String location) {
//    Table table = dynamoDB.getTable(TableName);
//
//    try {
//      // Increment the visit count.
//      // If visitor has never visited location, this will throw an exception.
//
//      Map<String, String> attrNames = new HashMap<String, String>();
//      attrNames.put("#viscnt", VisitCountAttr);
//
//      Map<String, Object> attrValues = new HashMap<String, Object>();
//      attrValues.put(":val", 1);
//
//      table.updateItem(FolloweeNameAtr, visitor, FollowerNameAtr, location,
//        "set #viscnt = #viscnt + :val", attrNames, attrValues);
//    }
//    catch (Exception e) {
//      // Record first visit of visitor to location
//
//      Item item = new Item()
//        .withPrimaryKey(FolloweeNameAtr, visitor, FollowerNameAtr, location)
//        .withNumber(VisitCountAttr, 1);
//
//      table.putItem(item);
//    }
//  }
//
//  /**
//   * Delete all visits of visitor to location
//   *
//   * @param visitor
//   * @param location
//   */
//  public void deleteVisit(String visitor, String location) {
//    Table table = dynamoDB.getTable(TableName);
//    table.deleteItem(FolloweeNameAtr, visitor, FollowerNameAtr, location);
//  }
//
//  /**
//   * Fetch the next page of locations visited by visitor
//   *
//   * @param visitor The visitor of interest
//   * @param pageSize The maximum number of locations to include in the result
//   * @param lastLocation The last location returned in the previous page of results
//   * @return The next page of locations visited by visitor
//   */
//  public ResultsPage getVisitedLocations(String visitor, int pageSize, String lastLocation) {
//    ResultsPage result = new ResultsPage();
//
//    Map<String, String> attrNames = new HashMap<String, String>();
//    attrNames.put("#vis", FolloweeNameAtr);
//
//    Map<String, AttributeValue> attrValues = new HashMap<>();
//    attrValues.put(":visitor", new AttributeValue().withS(visitor));
//
//    QueryRequest queryRequest = new QueryRequest()
//      .withTableName(TableName)
//      .withKeyConditionExpression("#vis = :visitor")
//      .withExpressionAttributeNames(attrNames)
//      .withExpressionAttributeValues(attrValues)
//      .withLimit(pageSize);
//
//    if (isNonEmptyString(lastLocation)) {
//      Map<String, AttributeValue> startKey = new HashMap<>();
//      startKey.put(FolloweeNameAtr, new AttributeValue().withS(visitor));
//      startKey.put(FollowerNameAtr, new AttributeValue().withS(lastLocation));
//
//      queryRequest = queryRequest.withExclusiveStartKey(startKey);
//    }
//
//    QueryResult queryResult = amazonDynamoDB.query(queryRequest);
//    List<Map<String, AttributeValue>> items = queryResult.getItems();
//    if (items != null) {
//      for (Map<String, AttributeValue> item : items){
//        String location = item.get(FollowerNameAtr).getS();
//        result.addValue(location);
//      }
//    }
//
//    Map<String, AttributeValue> lastKey = queryResult.getLastEvaluatedKey();
//    if (lastKey != null) {
//      result.setLastKey(lastKey.get(FollowerNameAtr).getS());
//    }
//
//    return result;
//  }
//
//  /**
//   * Fetch the next page of visitors who have visited location
//   *
//   * @param location The location of interest
//   * @param pageSize The maximum number of visitors to include in the result
//   * @param lastVisitor The last visitor returned in the previous page of results
//   * @return The next page of visitors who have visited location
//   */
//  public ResultsPage getVisitors(String location, int pageSize, String lastVisitor) {
//    ResultsPage result = new ResultsPage();
//
//    Map<String, String> attrNames = new HashMap<String, String>();
//    attrNames.put("#loc", FollowerNameAtr);
//
//    Map<String, AttributeValue> attrValues = new HashMap<>();
//    attrValues.put(":location", new AttributeValue().withS(location));
//
//    QueryRequest queryRequest = new QueryRequest()
//      .withTableName(TableName)
//      .withIndexName(IndexName)
//      .withKeyConditionExpression("#loc = :location")
//      .withExpressionAttributeNames(attrNames)
//      .withExpressionAttributeValues(attrValues)
//      .withLimit(pageSize);
//
//    if (isNonEmptyString(lastVisitor)) {
//      Map<String, AttributeValue> lastKey = new HashMap<>();
//      lastKey.put(FollowerNameAtr, new AttributeValue().withS(location));
//      lastKey.put(FolloweeNameAtr, new AttributeValue().withS(lastVisitor));
//
//      queryRequest = queryRequest.withExclusiveStartKey(lastKey);
//    }
//
//    QueryResult queryResult = amazonDynamoDB.query(queryRequest);
//    List<Map<String, AttributeValue>> items = queryResult.getItems();
//    if (items != null) {
//      for (Map<String, AttributeValue> item : items){
//        String visitor = item.get(FolloweeNameAtr).getS();
//        result.addValue(visitor);
//      }
//    }
//
//    Map<String, AttributeValue> lastKey = queryResult.getLastEvaluatedKey();
//    if (lastKey != null) {
//      result.setLastKey(lastKey.get(FolloweeNameAtr).getS());
//    }
//
//    return result;
//  }
}
