import java.util.*;
import java.util.concurrent.TimeUnit;

public class Main {

  public static void main(String[] args) {

    int TOTAL_ITEMS = 100;
    int TOTAL_NUM_USERS = 40; // 150;
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

    // Get the smallest List size between fName and lName
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

        // if the name/handle combination is already in the listOfHandleNames don't add
        boolean addName = true;
        for (String existingName : listOfNames) {
          if (existingName == name) {
            addName = false;
          }
        }
        if(addName) {
          listOfNames.add(name);
          listOfHandleNames.add(handleName);
          numOfEntries = numOfEntries + 1;
        }
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

      // Guarantees that the follower and followee are not the same person.
      // If they are the same, generate a new followee.
      while(follower == followee){
        followee = rand.nextInt(TOTAL_NUM_USERS);
      }


      // Check to see if it is already in the addedPartitionKeysList
      // Two of the same follower_handle and followee_handle combinations cannot be added into DynamoDB
      boolean alreadyInList = false;
      for(int k = 0; k < addedPartitionKeysList.size(); k++) {
        if (addedPartitionKeysList.get(k) == (listOfHandleNames.get(follower) + listOfHandleNames.get(followee))) {
          alreadyInList = true;
        }
      }
      // If not already in the list, add it.
      if(!alreadyInList) {
        testCases[i] = new Entry(listOfHandleNames.get(follower), listOfHandleNames.get(followee), listOfNames.get(followee), listOfNames.get(follower));
        addedPartitionKeysList.add(listOfHandleNames.get(follower) + listOfHandleNames.get(followee));
        i++;
      }
    }

    try {
      FollowsDAO followsDAO = new FollowsDAO();

      // PUT ALL 100 items
      for (int i = 0; i < testCases.length; i++) {
        followsDAO.put(testCases[i]);
        System.out.println("Inserting [" + (i + 1) + "]: " + testCases[i].toString());
      }


      // “GET” one of the items from the “follows” table using its primary key
      int randomUser = rand.nextInt(TOTAL_ITEMS);
      System.out.println("Attempting a GET on DynamoDB for: " + testCases[randomUser].toString());
      followsDAO.get(testCases[randomUser]);


      //“Update” the “follower_name” and “followee_name” attributes of one of the items in the “follows” table
      int randomUserToUpdate = rand.nextInt(TOTAL_ITEMS);
      Entry originalEntry = testCases[randomUserToUpdate];
      Entry newEntry = new Entry(testCases[randomUserToUpdate].follower_handle, testCases[randomUserToUpdate].followee_handle, testCases[randomUserToUpdate].followee_name, "Mr.THE_RICH_KING");
      System.out.println("Attempting an UPDATE on DynamoDB for: " + originalEntry.toString());
      System.out.println("Changing to: " + newEntry.toString());
      followsDAO.update(originalEntry, newEntry);


      //“Delete” one of the items in the “follows” table using its primary key
      int randomUserToDelete = rand.nextInt(TOTAL_ITEMS);
      System.out.println("Attempting a DELETE on DynamoDB for: " + testCases[randomUserToDelete].toString());
      followsDAO.delete(testCases[randomUserToDelete]);


      // TEST QUERY FOLLOWS TABLE
      Set<String> followeesQueryByFollowerHandle = new HashSet<>();
      ResultsPage results = null;
      // String queryFollowerHandle = "@AshleyMoore"; // Useful for a specific follower_handle Query
      String queryFollowerHandle = testCases[randomUser].follower_handle;
      while (results == null || results.hasLastKey()) {
        String lastFollowee = ((results != null) ? results.getLastKey() : null);
        results = followsDAO.getFolloweeHandleOfFollower(queryFollowerHandle, 10, lastFollowee);
        followeesQueryByFollowerHandle.addAll(results.getValues());
      }
      System.out.println("Followees Query By Follower Handle (" + queryFollowerHandle + ") Size: " + followeesQueryByFollowerHandle.size());
      for(String aFollowee : followeesQueryByFollowerHandle) {
        System.out.println(aFollowee + " is followed by " + queryFollowerHandle + ".");
      }
      // Useful if I already know what is in the database.
      // verify(followeesQueryByFollowerHandle.size() == 3, queryFollowerHandle + " follows 3 people");
      // verify(followeesQueryByFollowerHandle.contains("@AnthonyWalker"), "@AnthonyWalker is followed by " + queryFollowerHandle);
      // verify(followeesQueryByFollowerHandle.contains("@AshleyTaylor"), "@AshleyTaylor is followed by " + queryFollowerHandle);
      // verify(followeesQueryByFollowerHandle.contains("@DanielLewis"), "@DanielLewis is followed by " + queryFollowerHandle);


      //TEST QUERY follows_index TABLE
      System.out.println("Finding Followers for: " + testCases[randomUser].followee_handle);
      // TEST QUERY FOLLOWS TABLE
      Set<String> followersQueryByFolloweeHandle = new HashSet<>();
      ResultsPage results2 = null;
      // String queryFollowerHandle = "@AshleyMoore"; // Useful for a specific follower_handle Query
      String queryFolloweeHandle = testCases[randomUser].followee_handle;
      while (results2 == null || results2.hasLastKey()) {
        String lastFollower = ((results2 != null) ? results.getLastKey() : null);
        results2 = followsDAO.queryIndex(testCases[randomUser].followee_handle, 10, lastFollower);
        followersQueryByFolloweeHandle.addAll(results2.getValues());
      }
      System.out.println("Followers Query By Followee Handle (" + queryFolloweeHandle + ") Size: " + followersQueryByFolloweeHandle.size());
      for(String aFollower : followersQueryByFolloweeHandle) {
        System.out.println(aFollower + " follows " + queryFolloweeHandle + ".");
      }


    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  // Similar to assertion
  private static void verify(boolean b, String message) {
    if (!b) {
      throw new IllegalStateException(message);
    }
    else {
      System.out.println("Success! " + message + ".");
    }
  }
}