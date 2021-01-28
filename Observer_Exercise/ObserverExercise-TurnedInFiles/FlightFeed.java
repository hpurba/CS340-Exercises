import java.io.*;
import java.net.*;
import java.util.*;
import com.google.gson.*;


public class FlightFeed extends Subject {
	
	private static final String OPEN_SKY_BASE_URL = "https://opensky-network.org/api/states/all";
	
	// Flight property
	private Flight _flight;	
	public Flight getFlight() {
		return _flight;
	}

//	private void setFlight(Flight value) {
//		_flight = value;
//	}
	private void setFlight(Flight value) {
		_flight = value;
		updateObservers();
	}









	public void start() {
			
		FlightStates allFlights = getAllFlights();
		
		if (allFlights != null && allFlights.states.size() > 0) {
			
			// Monitor the first flight returned by Open Sky
			setFlight(allFlights.states.get(0));		
//			System.out.println(_flight.toString());
		
			while (true) {

				// Handles the delay
				try {
					final int UPDATE_DELAY_MILLIS = 10 * 1000; // one minute
					Thread.sleep(UPDATE_DELAY_MILLIS);
				}
				catch (InterruptedException ex) {
					return;
				}

				// Get latest flight info
				Flight newFlight = getSingleFlight(_flight);				
				
				if (newFlight != null) {				
				
					if (!_flight.equals(newFlight)) {
						
						// Flight info changed
						setFlight(newFlight);					
//						System.out.println(_flight.toString());
					}
				}
				else {
					// Flight disappeared from the data feed			
					setFlight(null);
//					System.out.println("Flight Over");
					
					return;
				}
			}
		}
	}
	
	private static FlightStates getAllFlights() {
		
		String url = OPEN_SKY_BASE_URL;
		
		return callOpenSky(url);
	}

	// This gets the flight
	private static Flight getSingleFlight(Flight flight) {
		
		final String url = OPEN_SKY_BASE_URL + "?icao24=" + flight.icao24;
		
		FlightStates flights = callOpenSky(url);
		
		if (flights != null) {
			
			if (flights.states != null && flights.states.size() > 0) {
				
				return flights.states.get(0);
			}
		}
		
		return null;
	}
	
	private static FlightStates callOpenSky(String url) {
		
		String jsonData = doGet(url);

		if (jsonData != null) {
			return parseFlights(jsonData);
		}
		else {
			return null;
		}
	}
	
	private static FlightStates parseFlights(String jsonData) {
			
		FlightStates flights = new FlightStates();
		
		JsonParser parser = new JsonParser();
		
		JsonObject rootObj = (JsonObject)parser.parse(jsonData);
		
		flights.time = rootObj.get("time").getAsInt();
		
		JsonElement statesElem = rootObj.get("states");
		if (statesElem instanceof JsonArray) {
			JsonArray statesArr = (JsonArray)statesElem;
			
			for (int i = 0; i < statesArr.size(); ++i) {
				Flight flight = parseFlight((JsonArray)statesArr.get(i));
				flights.states.add(flight);
			}
		}
		
		return flights;
	}

	// parses jsonArray and returns a flight object
	private static Flight parseFlight(JsonArray jsonArr) {
	
		Flight flight = new Flight();
		
		flight.icao24 = parseString(jsonArr.get(0));
		flight.callsign = parseString(jsonArr.get(1));
		flight.origin_country = parseString(jsonArr.get(2));
		flight.time_position = parseInt(jsonArr.get(3));
		flight.last_contact = parseInt(jsonArr.get(4));
		flight.longitude = parseFloat(jsonArr.get(5));
		flight.latitude = parseFloat(jsonArr.get(6));
		flight.baro_altitude = parseFloat(jsonArr.get(7));
		flight.on_ground = parseBoolean(jsonArr.get(8));
		flight.velocity = parseFloat(jsonArr.get(9));
		flight.true_track = parseFloat(jsonArr.get(10));
		flight.vertical_rate = parseFloat(jsonArr.get(11));
		flight.sensors = parseIntArray(jsonArr.get(12));
		flight.geo_altitude = parseFloat(jsonArr.get(13));
		flight.squawk = parseString(jsonArr.get(14));
		flight.spi = parseBoolean(jsonArr.get(15));
		flight.position_source = parseInt(jsonArr.get(16));

		return flight;
	}

	// parses a jsonElement to a Integer
	private static int parseInt(JsonElement jsonElem) {
		
		if (jsonElem instanceof JsonPrimitive) {
			return ((JsonPrimitive)jsonElem).getAsInt();
		}
		else {
			return 0;
		}
	}

	// parses a jsonElement to a float
	private static float parseFloat(JsonElement jsonElem) {
		
		if (jsonElem instanceof JsonPrimitive) {
			return ((JsonPrimitive)jsonElem).getAsFloat();
		}
		else {
			return 0.0f;
		}
	}

	// parses a jsonElement to a boolean
	private static boolean parseBoolean(JsonElement jsonElem) {
		
		if (jsonElem instanceof JsonPrimitive) {
			return ((JsonPrimitive)jsonElem).getAsBoolean();
		}
		else {
			return false;
		}
	}

	// Parses a json element to a string
	private static String parseString(JsonElement jsonElem) {
		
		if (jsonElem instanceof JsonPrimitive) {
			return ((JsonPrimitive)jsonElem).getAsString().trim();
		}
		else {
			return "";
		}
	}

	// Parses a JsonArray to a list of Integers
	private static List<Integer> parseIntArray(JsonElement jsonElem) {
		
		List<Integer> result = new ArrayList<Integer>();
		
		if (jsonElem instanceof JsonArray) {
			JsonArray jsonArr = (JsonArray)jsonElem;
			for (int i = 0; i < jsonArr.size(); ++i) {
				result.add(jsonArr.get(i).getAsInt());
			}
		}
		return result;
	}

	// Establishes a Http connection, gets response Data and returns it.
	private static String doGet(String urlStr) {
		try {
			URL url = new URL(urlStr);
			
			HttpURLConnection http = (HttpURLConnection)url.openConnection();
			
			http.setRequestMethod("GET");
			http.setDoOutput(false);
					
			http.connect();
			
			if (http.getResponseCode() == HttpURLConnection.HTTP_OK) {				
				InputStream respBody = http.getInputStream();			
				String respData = readString(respBody);
				return respData;
			}
			else {
				System.out.println("ERROR: " + http.getResponseMessage());
				return null;
			}
		}
		catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	// Reads inputStream and outputs it as a String
	private static String readString(InputStream is) throws IOException {
		
		StringBuilder sb = new StringBuilder();
		
		InputStreamReader sr = new InputStreamReader(is);
		char[] buf = new char[1024];	
		int len;
		while ((len = sr.read(buf)) > 0) {
			sb.append(buf, 0, len);
		}
		return sb.toString();
	}
}