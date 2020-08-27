package Control;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import Entity.Profile;
import Entity.Profile.Health;
import Entity.WeatherInfo.Weather;


/**
 * A class used to manage the user profile
 * Used to get the user profile and save the user profile
 */
public class ProfileManager {

	/**
	 * The path to store the profile info
	 */
	private final static String profile_path = "src/main/resources/profile.properties";
	
	//Singleton design pattern
	/**
	 * The ProfileManager instance, used as a singleton
	 */
	private static final ProfileManager INSTANCE = new ProfileManager();

	/**
	 * A function to get the instance of ProfileManager object
	 * @return The ProfileManager object
	 */
	public static ProfileManager getInstance() {
		return INSTANCE;
	}

	/**
	 * A function to save the profile
	 * @param name The username
	 * @param healthCondition A list of health condition selected by user
	 * @param notifyPM25 A boolean variable indication whether the user wants to be notified of PM25
	 * @param notifyUVI A boolean variable indication whether the user wants to be notified of UVI
	 * @param notifyPSI A boolean variable indication whether the user wants to be notified of PSI
	 * @param notifyWeather A boolean variable indication whether the user wants to be notified of weather condition
	 * @param PM25 The PM25 level to be notified of
	 * @param UVI The UVI level to be notified of
	 * @param PSI The PSI level to be notified of
	 * @param weather The weather condition to be notified of
	 * @return True if profile saved successfully, False if unable to save profile
	 */
	public static boolean saveProfile(
			String name, List<String> healthCondition, boolean notifyPM25, 
			boolean notifyUVI, boolean notifyPSI, boolean notifyWeather,
			int PM25, int UVI, int PSI, List<String> weather) throws IOException {
		
		try (OutputStream out = new FileOutputStream(ProfileManager.profile_path)) {
			
			Properties prop = new Properties();
			prop.setProperty("user.name", name);
			prop.setProperty("user.healthCondition", String.join(",", healthCondition));
			prop.setProperty("user.notifyPM25", String.valueOf(notifyPM25));
			prop.setProperty("user.notifyUVI", String.valueOf(notifyUVI));
			prop.setProperty("user.notifyPSI", String.valueOf(notifyPSI));
			prop.setProperty("user.notifyWeather", String.valueOf(notifyWeather));
			prop.setProperty("user.PM25", String.valueOf(PM25));
			prop.setProperty("user.UVI", String.valueOf(UVI));
			prop.setProperty("user.PSI", String.valueOf(PSI));
			prop.setProperty("user.weather", String.join(",", weather));
			
			prop.store(out, null);
			return true;
			
		}
		catch (IOException e) {
			
			e.printStackTrace();
			return false;
			
		}
		
	}
	
	public static Profile getProfile() throws FileNotFoundException, IOException {
		
		Profile profile = new Profile();
		
		try(InputStream in = new FileInputStream(ProfileManager.profile_path)) {
			
			Properties prop = new Properties();
			prop.load(in);
			
			profile.setName(prop.getProperty("user.name"));
			profile.setHealthCondition(
					prop.getProperty("user.healthCondition").split(",")
					);
			profile.setNotifyPM25(
					Boolean.parseBoolean(prop.getProperty("user.notifyPM25"))
					);
			profile.setNotifyPSI(
					Boolean.parseBoolean(prop.getProperty("user.notifyPSI"))
					);
			profile.setNotifyUVI(
					Boolean.parseBoolean(prop.getProperty("user.notifyUVI"))
					);
			profile.setNotifyWeather(
					Boolean.parseBoolean(prop.getProperty("user.notifyWeather"))
					);
			profile.setPM25(
					Integer.parseInt(prop.getProperty("user.PM25"))
					);
			profile.setPSI(
					Integer.parseInt(prop.getProperty("user.PSI"))
					);
			profile.setUVI(
					Integer.parseInt(prop.getProperty("user.UVI"))
					);
			profile.setWeather(
					prop.getProperty("user.weather").split(",")
					);		
			
		}
		catch (Exception e) {
			
			//default values if profile not found
			profile.setName("");
			profile.setHealthCondition(Arrays.asList(Health.HEALTHY));
			profile.setNotifyPM25(true);
			profile.setNotifyPSI(true);
			profile.setNotifyUVI(true);
			profile.setNotifyWeather(true);
			profile.setPM25(150);
			profile.setPSI(100);
			profile.setUVI(6);
			profile.setWeather(Arrays.asList(Weather.ALL));	
			e.printStackTrace();
			
		}
		
		return profile;
		
	}
	
}
