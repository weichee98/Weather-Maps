package Entity;

/**
 * An entity class, used to represent weather information
 */
public class WeatherInfo {

	/**
	 * Enumeration of types of weather
	 */
	public static enum Weather {ALL, THUNDERY, RAIN, SHOWERS, CLOUDY, WINDY, HAZY, FAIR};

	private int pm25; 
	private int psi;
	private int uvi;
	private String weather_str;
	private Weather weather;

	/**
	 * Constructor function
	 * @param pm25 pm25
	 * @param psi	psi
	 * @param uvi uvi
	 * @param weather_str weather as string type
	 * @param weather weather as enum type
	 */
	public WeatherInfo(int pm25, int psi, int uvi, String weather_str, Weather weather) {
		super();
		this.pm25 = pm25;
		this.psi = psi;
		this.uvi = uvi;
		this.weather_str = weather_str;
		this.weather = weather;
	}

	/**
	 * Getter for PM2.5
	 * @return pm2.5
	 */
	public int getPM25() {
		return pm25;
	}

	/**
	 * Getter for PSI
	 * @return PSI
	 */
	public int getPSI() {
		return psi;
	}

	/**
	 * Getter for UVI
	 * @return UVI
	 */
	public int getUVI() {
		return uvi;
	}

	/**
	 * Getter for weather in string type
	 * @return weather in string type
	 */
	public String getWeather_str() {
		return weather_str;
	}

	/**
	 * Getter for weather in Weather(enum)type
	 * @return weather in Weather(enum)type
	 */
	public Weather getWeather() {
		return weather;
	}
	
	
}
