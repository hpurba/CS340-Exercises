public class FlightDeltaReporter implements Observer {
    FlightFeed feed;

    public FlightDeltaReporter(FlightFeed feed) {
        this.feed = feed;
        feed.register(this);
    }

    public void update() {
        Flight flight = feed.getFlight();
        if (flight != null) {
            System.out.println(String.format("DELTA: lon: %f, lat: %f, vel: %f, alt: %f", flight.longitude, flight.latitude, flight.velocity, flight.geo_altitude));
        }
//            System.out.println("STATUS: " + flight.toString());
        else
            System.out.println("STATUS: no flight");
    }
}
