package Entity;


/**
 * An entity class, used to represent a location
 */
public class Location {

	/**
	 * The name of Location
	 */
	private String name;
	/**
	 * The latitude of Location
	 */
	private double latitude;
	/**
	 * The longitude of Location
	 */
	private double longitude;

	/**
	 * A Constructor function
	 * @param name Name of location
	 * @param latitude Latitude of location
	 * @param longitude Longitude of location
	 */
	public Location(String name, double latitude, double longitude) {
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Location() {
	}

	/**
	 * A getter function for name
	 * @return name of the location
	 */
	public String getName() {
		return name;
	}

	/**
	 * A getter function for latitude
	 * @return latitude of the location
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * A getter function for longitude
	 * @return longitude of the location
	 */
	public double getLongitude() {
		return longitude;
	}
	
	/*
	public void setName(String name) {
		this.name = name;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}	
	*/
//	Testing and debugging
//	public void print(){
//		System.out.println("Name: "+ this.name+ " Longitude: "+ this.longitude+" Latitude: "+this.latitude);
//	}
}
