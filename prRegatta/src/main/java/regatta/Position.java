package regatta;

public class Position {
	private double longitude;
	private double latitude;
	private static final double  mToKm= 1.60934;
	/**
	 * Creates a new position
	 * @param lat  	latitude
	 * @param lon	longitude
	 */
	public Position(double lat, double lon) {
		latitude = normalizeLat(lat);
		longitude = normalizeLon(lon);
	}
	
	/**
	 * Latitude is returned
	 * @return the latitude
	 */
	public double getLatitude() {
		return latitude;
	}
	
	/**
	 * Longitude is returned
	 * @return the longitude
	 */
	public double getLongitude() {
		return longitude;
	}
		
	/**
	 * Latitude is normalized
	 * @param lat initial latitude
	 * @return normalized latitude
	 */
	private double normalizeLat(double lat) {
		double res = lat % 360;
		if (res < 0) {
			res = res + 360;
		}
		// Now it is a number between 0 and 360
		if (res > 90 && res <= 270) {
			res = 180 - res;
		} else if (res > 270 && res < 360) {
			res = res - 360;
		}
		return res;
	}
	
	/**
	 * Longitude is normalized
	 * @param lon initial longitude
	 * @return normalized longitude
	 */
	private double normalizeLon(double lon) {
		double res = lon % 360;
		if (res < 0) {
			res += 360;
		}
		return res;
	}
	
	@Override
	public String toString() {
		return String.format("l= %4.2f L= %4.2f", latitude, longitude);
	}
	
	/**
	 * The method computes the distance between the receiver and the given position
	 * @param p position
	 * @return distance in km
	 */
	public double distance(Position p) {
		return (Math.sqrt(Math.pow(Math.cos(latitude)*(longitude-p.longitude), 2) + Math.pow(latitude-p.latitude, 2)))*mToKm;
	}
	
	/**
	 * Position if the ship starts in the receiver position and moves in the given direction at the given speed during
	 * the given time 
	 * @param minutes	Minutes
	 * @param direction		Direction
	 * @param speed	Speed (km/h)
	 * @return Final Position
	 */
	public Position positionAfterMoving(int minutes, int direction, int speed) {
		double angle = (360+90-direction)%360*(2*Math.PI)/360;
		double lon = longitude + speed/mToKm * Math.cos(angle)*Math.cos(latitude*Math.PI/180)*minutes/60;
		double lat = latitude + speed/mToKm * Math.sin(angle)*minutes/60;
		return new Position(lat, lon);
	}
}
