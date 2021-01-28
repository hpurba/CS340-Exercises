
public class FlightMonitor {
	
	public static void main(String[] args) {
	
		FlightFeed feed = new FlightFeed();
		FlightStatusReporter flightStatusReporter = new FlightStatusReporter(feed);
		FlightDeltaReporter flightDeltaReporter = new FlightDeltaReporter(feed);
		feed.start();
	}
}