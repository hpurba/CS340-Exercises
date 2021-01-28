import java.util.*;

public class Flight implements Cloneable {
	
	public String icao24 = "";
	public String callsign = "";
	public String origin_country = "";
	public int time_position = 0;
	public int last_contact = 0;
	public float longitude = 0.0f;
	public float latitude = 0.0f;
	public float baro_altitude = 0.0f;
	public boolean on_ground = false;
	public float velocity = 0.0f;
	public float true_track = 0.0f;
	public float vertical_rate = 0.0f;
	public List<Integer> sensors = new ArrayList<Integer>();
	public float geo_altitude = 0.0f;
	public String squawk = null;
	public boolean spi = false;
	public int position_source = 0;
	
	public Flight() {
		return;
	}
	
	public Flight(Flight f) {
		this.icao24 = f.icao24;
		this.callsign = f.callsign;
		this.origin_country = f.origin_country;
		this.time_position = f.time_position;
		this.last_contact = f.last_contact;
		this.longitude = f.longitude;
		this.latitude = f.latitude;
		this.baro_altitude = f.baro_altitude;
		this.on_ground = f.on_ground;
		this.velocity = f.velocity;
		this.true_track = f.true_track;
		this.vertical_rate = f.vertical_rate;
		this.sensors = new ArrayList<Integer>(f.sensors);
		this.geo_altitude = f.geo_altitude;
		this.squawk = f.squawk;
		this.spi = f.spi;
		this.position_source = f.position_source;
	}
	
	@Override
	public Flight clone() {
		return new Flight(this);
	}
	
	@Override
	public String toString() {
		return String.format("id: %s, call: %s, country: %s, lon: %f, lat: %f, vel: %f, alt: %f",
								icao24, callsign, origin_country, longitude, latitude, velocity, geo_altitude);
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		if (o == this) {
			return true;
		}
		if (this.getClass() != o.getClass()) {
			return false;
		}
		
		Flight f = (Flight)o;
		return (Objects.equals(this.icao24, f.icao24) &&
				Objects.equals(this.callsign, f.callsign) &&
				Objects.equals(this.origin_country, f.origin_country) &&
				this.time_position == f.time_position &&
				this.last_contact == f.last_contact &&
				this.longitude == f.longitude &&
				this.latitude == f.latitude &&
				this.baro_altitude == f.baro_altitude &&
				this.on_ground == f.on_ground &&
				this.velocity == f.velocity &&
				this.true_track == f.true_track &&
				this.vertical_rate == f.vertical_rate &&
				Objects.deepEquals(this.sensors, f.sensors) &&
				this.geo_altitude == f.geo_altitude &&
				Objects.equals(this.squawk, f.squawk) &&
				this.spi == f.spi &&
				this.position_source == f.position_source);
	}
	
}