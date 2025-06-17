package prScooters;

public class Position {
	
	// instance variables: latitude and longitude
	private double latitude, longitude;
	
	/*
	 *  constructor: a Position object is created 
	 *  given a latitude (lat) and a longitude (lon)
	 *  lat and lon are the input parameters
	 */
	public Position(double lat, double lon) {
		latitude = lat;
		longitude = lon;
	}
	
	// latitude is returned
	public double getLatitude() {
		return latitude;
	}

	// longitude is returned
	public double getLongitude() {
		return longitude;
	}

	// latitude is modified
	public void setLatitude(double lat) {
		latitude = lat;
	}

	// longitude is modified
	public void setLongitude(double lon) {
		longitude = lon;
	}

	/* it calculates and returns the distance between
	 * the position of the receiver object (this)
	 * and the position of the object received as input parameter (p)
	 */
	public double distance(Position p) {
		return Math.sqrt(Math.pow(this.latitude - p.latitude, 2) +
			   Math.pow(this.longitude - p.longitude, 2)); 
	}
	
	/*
	 *  returns the string representation for a Position object
	 */
	public String toString() {
		return "Lat: " + latitude + "; Lon: " + longitude;
	}
}
