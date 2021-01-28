/**
 * This program's main method is in this FlightMonitor class
 * FlightMonitor that selects a random airline flight that is currently in the air, and monitors its progress until it lands
 * @author Hikaru Purba
 */
public class FlightMonitor {
	
	public static void main(String[] args) {
	
		FlightFeed feed = new FlightFeed();
		FlightStatusReporter flightStatusReporter = new FlightStatusReporter(feed);
		FlightDeltaReporter flightDeltaReporter = new FlightDeltaReporter(feed);
		feed.start();
	}
}