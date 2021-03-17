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
//      for(int k = 0; k < addedPartitionKeysList.size(); k++){
//        if(addedPartitionKeysList.get(k) == listOfHandleNames.get(follower)) {
//          alreadyInList = true;
//        }
//      }
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
      for(int i = 0; i < testCases.length; i++){
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
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}