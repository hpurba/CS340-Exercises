import java.util.*;
import java.util.concurrent.TimeUnit;

public class Main {

  public static void main(String[] args) {

    int TOTAL_ITEMS = 100;
    int TOTAL_NUM_USERS = 150;
    int numOfEntries = 0;

    List<String> addedPartitionKeysList = new ArrayList<String>();

    // Generate Random Names
    List<String> listOfNames = new ArrayList<String>();
    List<String> listOfHandleNames = new ArrayList<String>();
    List<String> fName = Arrays.asList("James", "John", "Robert", "Michael", "William", "David", "Richard", "Joseph",
      "Thomas", "Charles", "Christopher", "Daniel", "Matthew", "Anthony", "Mary", "Patricia", "Jennifer", "Linda",
      "Elizabeth", "Barbara", "Susan", "Jessica", "Sarah", "Karen", "Nancy", "Lisa", "Margaret", "Betty", "Sandra", "Ashley");
    Collections.shuffle(fName);
    List<String> lName = Arrays.asList("Smith", "Johnson", "Cooper", "Bing", "Williams", "Brown", "Jones", "Oldham", "Miller",
      "Davis", "Swenson", "Swanson", "Garcia", "Rodriguez", "Wilson", "Martinez", "Anderson", "Taylor", "Thomas", "Hernandez",
      "Moore", "Martin", "Jackson", "Thompson", "White", "Lopez", "Lee", "Gonzalez", "Harris", "Clark", "Lewis", "Robinson",
      "Walker", "Perez", "Hall");
    Collections.shuffle(lName);

    // Total combinations made
    int lowestSizeOfCombinedList = 0;
    if(fName.size() > lName.size()) {
      lowestSizeOfCombinedList = lName.size();
    } else {
      lowestSizeOfCombinedList = fName.size();
    }

    while(numOfEntries <= TOTAL_NUM_USERS){
      for(int i = 0; i < lowestSizeOfCombinedList; i++){
        String name;
        name = fName.get(i) + lName.get(i);
        String handleName = "@" + name;
        listOfNames.add(name);
        listOfHandleNames.add(handleName);
        numOfEntries = numOfEntries + 1;
      }
      Collections.shuffle(fName);
      Collections.shuffle(lName);
    }

    // Make array of Entries
    Entry[] testCases = new Entry[TOTAL_ITEMS];
    Random rand = new Random();
    for (int i = 0; i < TOTAL_ITEMS;){
      int follower = rand.nextInt(TOTAL_NUM_USERS);
      int followee = rand.nextInt(TOTAL_NUM_USERS);

      // Check to see if it is already in the addedPartitionKeysList
      // Two of the same follower_handle cannot be added into DynamoDB
      boolean alreadyInList = false;
      for(int k = 0; k < addedPartitionKeysList.size(); k++){
        if(addedPartitionKeysList.get(k) == listOfHandleNames.get(follower)) {
          alreadyInList = true;
        }
      }

      // If not already in the list, add it.
      if(!alreadyInList) {
        testCases[i] = new Entry(listOfHandleNames.get(follower), listOfHandleNames.get(followee), listOfNames.get(followee), listOfNames.get(follower));
        addedPartitionKeysList.add(listOfHandleNames.get(follower));
        i++;
      }
    }



    try {
      FollowsDAO followsDAO = new FollowsDAO();

      // PUT ALL 100 items
      for(int i = 0; i < testCases.length; i++){
        followsDAO.put(testCases[i]);
        System.out.println("Inserting [" + (i + 1) + "]: " + testCases[i].toString());
      }

      // “GET” one of the items from the “follows” table using its primary key
      int randomUser = rand.nextInt(TOTAL_NUM_USERS);
      System.out.println("Attempting a GET on DynamoDB for: " + testCases[randomUser].toString());
      followsDAO.get(testCases[randomUser]);


      //“Update” the “follower_name” and “followee_name” attributes of one of the items in the “follows” table
      //“Delete” one of the items in the “follows” table using its primary key


      // UPDATE?
//      for (Entry tc : testCases) {
//        for (int i = 0; i < tc.follower_name; ++i) {
//          followsDAO.recordVisit(tc.follower_handle, tc.followee_handle);
//        }
//      }

//      // TODO: GET
//      for (Entry tc : testCases) {
//        for (int i = 0; i < tc.follower_name; ++i) {
//          String message = String.format("%s visited %s %d times", tc.follower_handle, tc.followee_handle, tc.follower_name);
//          verify(followsDAO.getVisitCount(tc.follower_handle, tc.followee_handle) == tc.follower_name, message);
//        }
//      }
//
//      testGetVisitedLocations(followsDAO, 1);
//      testGetVisitedLocations(followsDAO, 2);
//      testGetVisitedLocations(followsDAO, 3);
//      testGetVisitedLocations(followsDAO, 10);
//
//      testGetVisitors(followsDAO, 1);
//      testGetVisitors(followsDAO, 2);
//      testGetVisitors(followsDAO, 3);
//      testGetVisitors(followsDAO, 10);
//
//      for (Entry tc : testCases) {
//        for (int i = 0; i < tc.follower_name; ++i) {
//          followsDAO.deleteVisit(tc.follower_handle, tc.followee_handle);
//        }
//      }
//
//      for (Entry tc : testCases) {
//        for (int i = 0; i < tc.follower_name; ++i) {
//          String message = String.format("Deleted visits of %s to %s", tc.follower_handle, tc.followee_handle);
//          verify(followsDAO.getVisitCount(tc.follower_handle, tc.followee_handle) == 0, message);
//        }
//      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }


//  TODO: REMOVE, THESE ARE TESTS, but the code could be useful.
//
//  private static void testGetVisitedLocations(FollowsDAO followsDAO, int pageSize) {
//
//    Set<String> bettysLocations = new HashSet<>();
//
//    ResultsPage results = null;
//    while (results == null || results.hasLastKey()) {
//      String lastLocation = ((results != null) ? results.getLastKey() : null);
//      results = followsDAO.getVisitedLocations("Betty", pageSize, lastLocation);
//      bettysLocations.addAll(results.getValues());
//    }
//
//    verify(bettysLocations.size() == 3, "Betty visited three locations");
//    verify(bettysLocations.contains("New York"), "Betty visited New York");
//    verify(bettysLocations.contains("Atlanta"), "Betty visited Atlanta");
//    verify(bettysLocations.contains("Boston"), "Betty visited Boston");
//  }
//
//  private static void testGetVisitors(FollowsDAO followsDAO, int pageSize) {
//    Set<String> newYorksVisitors = new HashSet<>();
//
//    ResultsPage results = null;
//    while (results == null || results.hasLastKey()) {
//      String lastVisitor = ((results != null) ? results.getLastKey() : null);
//      results = followsDAO.getVisitors("New York", pageSize, lastVisitor);
//      newYorksVisitors.addAll(results.getValues());
//    }
//
//    verify(newYorksVisitors.size() == 2, "New York has two visitors");
//    verify(newYorksVisitors.contains("Fred"), "Fred visited New York");
//    verify(newYorksVisitors.contains("Betty"), "Betty visited New York");
//  }
//
//  private static void verify(boolean b, String message) {
//    if (!b) {
//      throw new IllegalStateException(message);
//    }
//  }
}