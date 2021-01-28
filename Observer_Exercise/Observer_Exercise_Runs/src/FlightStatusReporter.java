public class FlightStatusReporter implements Observer {
  FlightFeed feed;

  public FlightStatusReporter(FlightFeed feed) {
    this.feed = feed;
    feed.register(this);
  }

  public void update() {
    Flight flight = feed.getFlight();
    if (flight != null)
      System.out.println("STATUS: " + flight.toString());
    else
      System.out.println("STATUS: no flight");
  }
}
