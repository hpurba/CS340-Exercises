import java.util.HashSet;
import java.util.Set;

public class Main {

	public static void main(String[] args) {

		try {
			TestCase[] testCases = {
					new TestCase("Fred", "New York", 1),
					new TestCase("Wilma", "San Diego", 3),
					new TestCase("Barney", "Vernal", 0),
					new TestCase("Betty", "New York", 12),
					new TestCase("Betty", "Atlanta", 7),
					new TestCase("Betty", "Boston", 5)
			};

			VisitsDao visitsDao = new VisitsDao();

			try {
				// This will throw an exception if the table does not exist
				visitsDao.deleteTable();
			}
			catch (DataAccessException e2) {
				// Ignore
			}

			visitsDao.createTable();

			for (TestCase tc : testCases) {
				for (int i = 0; i < tc.visitCount; ++i) {
					visitsDao.recordVisit(tc.visitor, tc.location);
				}
			}

			for (TestCase tc : testCases) {
				for (int i = 0; i < tc.visitCount; ++i) {
					String message = String.format("%s visited %s %d times", tc.visitor, tc.location, tc.visitCount);
					verify(visitsDao.getVisitCount(tc.visitor, tc.location) == tc.visitCount, message);
				}
			}

			testGetVisitedLocations(visitsDao, 1);
			testGetVisitedLocations(visitsDao, 2);
			testGetVisitedLocations(visitsDao, 3);
			testGetVisitedLocations(visitsDao, 10);

			testGetVisitors(visitsDao, 1);
			testGetVisitors(visitsDao, 2);
			testGetVisitors(visitsDao, 3);
			testGetVisitors(visitsDao, 10);

			for (TestCase tc : testCases) {
				for (int i = 0; i < tc.visitCount; ++i) {
					visitsDao.deleteVisit(tc.visitor, tc.location);
				}
			}

			for (TestCase tc : testCases) {
				for (int i = 0; i < tc.visitCount; ++i) {
					String message = String.format("Deleted visits of %s to %s", tc.visitor, tc.location);
					verify(visitsDao.getVisitCount(tc.visitor, tc.location) == 0, message);
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void testGetVisitedLocations(VisitsDao visitsDao, int pageSize) {
		Set<String> bettysLocations = new HashSet<>();

		ResultsPage results = null;
		while (results == null || results.hasLastKey()) {
			String lastLocation = ((results != null) ? results.getLastKey() : null);
			results = visitsDao.getVisitedLocations("Betty", pageSize, lastLocation);
			bettysLocations.addAll(results.getValues());
		}

		verify(bettysLocations.size() == 3, "Betty visited three locations");
		verify(bettysLocations.contains("New York"), "Betty visited New York");
		verify(bettysLocations.contains("Atlanta"), "Betty visited Atlanta");
		verify(bettysLocations.contains("Boston"), "Betty visited Boston");
	}

	private static void testGetVisitors(VisitsDao visitsDao, int pageSize) {
		Set<String> newYorksVisitors = new HashSet<>();

		ResultsPage results = null;
		while (results == null || results.hasLastKey()) {
			String lastVisitor = ((results != null) ? results.getLastKey() : null);
			results = visitsDao.getVisitors("New York", pageSize, lastVisitor);
			newYorksVisitors.addAll(results.getValues());
		}

		verify(newYorksVisitors.size() == 2, "New York has two visitors");
		verify(newYorksVisitors.contains("Fred"), "Fred visited New York");
		verify(newYorksVisitors.contains("Betty"), "Betty visited New York");
	}

	private static void verify(boolean b, String message) {
		if (!b) {
			throw new IllegalStateException(message);
		}
	}
}

class TestCase {
	public String visitor;
	public String location;
	public int visitCount;

	public TestCase(String _visitor, String _location, int _visitCount) {
		visitor = _visitor;
		location = _location;
		visitCount = _visitCount;
	}

	@Override
	public String toString() {
		return String.format("TestCase[%s, %s, %d]", visitor, location, visitCount);
	}
}
