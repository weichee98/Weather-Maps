package Entity;

import java.util.ArrayList;
import java.util.List;

import Entity.WeatherInfo.Weather;

/**
 * An entity class, used to represent a user profile
 */
public class Profile {

	/**
	 * An enumeration containing the health conditions
	 */
	public static enum Health {HEALTHY, ASTHMA, SKIN_CANCER, OTHERS};

	/**
	 * Name of the user
	 */
	private String name;

	/**
	 * List of health conditions of user
	 */
	private List<Health> healthCondition;

	/**
	 * Whether user wants to get notification/warning on PM2.5
	 */
	private boolean notifyPM25;

	/**
	 * Whether user wants to get notification/warning on UVI
	 */
	private boolean notifyUVI;

	/**
	 * Whether user wants to get notification/warning on PSI
	 */
	private boolean notifyPSI;

	/**
	 * Whether user wants to get notification/warning on weather
	 */
	private boolean notifyWeather;

	/**
	 * The PM2.5 value when user will be notified/warned
	 */
	private int PM25;

	/**
	 * The UVI value when user will be notified/warned
	 */
	private int UVI;

	/**
	 * The PSI value when user will be notified/warned
	 */
	private int PSI;

	/**
	 * The types of weather which user will be notified/warned
	 */
	private List<Weather> weather;

	/**
	 * Getter function for name of user
	 * @return name of user
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter function for name of user
	 * @param name The name to be set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter function for health condition of user
	 * @return A list of health conditions of user
	 */
	public List<Health> getHealthCondition() {
		return healthCondition;
	}

	/**
	 * Setter function for health conditions of user using Health from enumeration
	 * @param healthCondition The health conditions to be set
	 */
	public void setHealthCondition(List<Health> healthCondition) {
		this.healthCondition = healthCondition;
	}

	/**
	 * A function to set health conditions of user using String type of health condition
	 * @param healthCondition The list of health conditions in string to be added into the user health condition
	 */
	public void setHealthCondition(String[] healthCondition) {
		List<Health> h = new ArrayList<Health>();
		try {
			for (String s: healthCondition) {
				h.add(Health.valueOf(s));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		this.healthCondition = h;
	}

	/**
	 * user decision on getting notify on UVI
	 * @return whether user want to get notify on PM2.5
	 */
	public boolean isNotifyPM25() {
		return notifyPM25;
	}

	public void setNotifyPM25(boolean notifyPM25) {
		this.notifyPM25 = notifyPM25;
	}

	/**
	 * user decision on getting notification on UVI
	 * @return whether user want to get notify on UVI
	 */
	public boolean isNotifyUVI() {
		return notifyUVI;
	}


	/**
	 * Setter on user decision on getting notification on UVI
	 * @param notifyUVI User decision on whether to get notify on UVI
	 */
	public void setNotifyUVI(boolean notifyUVI) {
		this.notifyUVI = notifyUVI;
	}

	/**
	 * user decision on getting notification on PSI
	 * @return whether user wants to get notify on PSI
	 */
	public boolean isNotifyPSI() {
		return notifyPSI;
	}

	/**
	 * Setter on user decision on getting notification on PSI
	 * @param notifyPSI User decision on whether to get notify on PSI
	 */
	public void setNotifyPSI(boolean notifyPSI) {
		this.notifyPSI = notifyPSI;
	}

	/**
	 * user decision on getting notification on weather
	 * @return whether user wants to get notify on weather
	 */
	public boolean isNotifyWeather() {
		return notifyWeather;
	}

	/**
	 * Setter on user decision on getting notification on weather
	 * @param notifyWeather User decision on whether to get notify on weather
	 */
	public void setNotifyWeather(boolean notifyWeather) {
		this.notifyWeather = notifyWeather;
	}

	/**
	 * Getter for PM 2.5 user set
	 * @return PM 2.5 value user set to be notified
	 */
	public int getPM25() {
		return PM25;
	}

	/**
	 * Setter for Pm2.5 value to be notified
	 * @param pM25 PM2.5 value user wants to set for notification
	 */
	public void setPM25(int pM25) {
		PM25 = pM25;
	}

	/**
	 * Getter for UVI user set
	 * @return UVI value user set to be notified
	 */
	public int getUVI() {
		return UVI;
	}

	/**
	 * Setter for UVI value to be notified
	 * @param uVI UVI value user wants to set for notification
	 */
	public void setUVI(int uVI) {
		UVI = uVI;
	}

	/**
	 * Getter for PSI user set
	 * @return PSI value user set to be notified
	 */
	public int getPSI() {
		return PSI;
	}

	/**
	 * Setter for PSI value to be notified
	 * @param pSI PSI value user wants to set for notification
	 */
	public void setPSI(int pSI) {
		PSI = pSI;
	}

	/**
	 * Getter for weathers set by user
	 * @return Weathers user set to be notified
	 */
	public List<Weather> getWeather() {
		return weather;
	}

	/**
	 * Setter for weathers to get notification
	 * @param weather list of weather user wants to set
	 */
	public void setWeather(List<Weather> weather) {
		this.weather = weather;
	}

	/**
	 * Setter fir weathers to get notification
	 * @param weather Strings of weather user wants to set
	 */
	public void setWeather(String[] weather) {
		List<Weather> w = new ArrayList<Weather>();
		try {
			for (String s: weather) {
				w.add(Weather.valueOf(s));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		this.weather = w;
	}

}
