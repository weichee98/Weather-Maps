package Control;

import Entity.Profile;
import Entity.WeatherInfo;
import Entity.WeatherInfo.Weather;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.IOException;


/**
 * The logic to determine suitable warnings and recommendations to be given to users based on the current weather condition
 */
public final class WarningLogic {

    /**
     * Profile info
     */
    private static Profile profile;

    /**
     * Weather info set based on input
     */
    private static WeatherInfo weather;


    /**
     * A constructor for WarningLogic
     * @param wthr The input WeatherInfo
     */
    public WarningLogic(WeatherInfo wthr) throws IOException {
        profile = ProfileManager.getProfile();
        weather = wthr;
    }

    /**
     * A function to organise/compile the warning text for different indicators
     * @return A TextFlow containing warning text for different indicators
     */
    public TextFlow getWarning(){
        TextFlow warning = new TextFlow();
        warning.getChildren().add(checkUV());
        warning.getChildren().add(checkPM2_5());
        warning.getChildren().add(checkPSI());
        warning.getChildren().add(checkWeather());

        return warning;
    }

    /**
     * A function to check whether user choose to get warning and whether the UVI exceeds value chosen by the user
     * @return A coloured Text of UV
     */
    private static Text checkUV(){
        Text s = new Text();
        if (weather.getUVI() == -1)
        	s.setText("UV index unavailable\n");
        else
        	s.setText("UV index is at: "+weather.getUVI()+"\n");
        if(profile.isNotifyUVI()){
            if(weather.getUVI() >= profile.getUVI()){
                s.setFill(Color.RED);
            }
        }
        return s;
    }

    /**
     * A function to check whether user choose to get warning and whether the Pm2.5 exceeds value chosen by the user
     * @return  A coloured Text of PM2.5
     */
    private static Text checkPM2_5(){
        Text s = new Text();
        if (weather.getPM25() == -1)
        	s.setText("PM2.5 value is unavailable\n");
        else
        	s.setText("PM2.5 value is at: "+weather.getPM25()+" \n");
        if(profile.isNotifyPM25()) {
            if (weather.getPM25() >= profile.getPM25()) {
                s.setFill(Color.RED);
            }
        }
        return s;
    }

    /**
     * A function to check whether user choose to get warning and whether the PSI exceeds value chosen by the user
     * @return A coloured Text of PSI
     */
    private static Text checkPSI(){
        Text s = new Text();
        if (weather.getPSI() == -1)
        	s.setText("PSI value is unavailable\n");
        else
        	s.setText("PSI value is at: "+weather.getPSI()+"\n");
        if(profile.isNotifyPSI()) {
            if (weather.getPSI() >= profile.getPSI()) {
                s.setFill(Color.RED);
            }
        }
        return s;
    }

    /** A function to check whether user choose to get warning and whether the weather match the chosen weather of user
     * @return A coloured Text of weather
     */
    private static Text checkWeather(){
        Text s = new Text();
        if (weather.getWeather() == Weather.ALL)
        	s.setText("Weather information unavailable\n");
        else
        	s.setText("It is currently: "+weather.getWeather_str()+"\n");
        if(profile.isNotifyWeather()){
            if(profile.getWeather().contains(weather.getWeather()) | 
            		profile.getWeather().contains(Weather.ALL)){
                s.setFill(Color.RED);
            }
        }
        return s;
    }

    /**
     * A function to decide the recommendation messages based on indicators
     * @return Textflow of recommendation messages
     */
    public TextFlow recommendation(){
        TextFlow rcmd = new TextFlow();
        int psi = weather.getPSI();
        int pm25 = weather.getPM25();
        int uvi = weather.getUVI();
        Weather wthr_cond = weather.getWeather();
        
        Text s = new Text();
        Text r = new Text();
        Text psi_txt = new Text();
        Text pm25_txt = new Text();
        Text uvi_txt = new Text();
        rcmd.getChildren().addAll(s, psi_txt, pm25_txt, uvi_txt, r);

        if ((wthr_cond == Weather.RAIN)
        |(wthr_cond == Weather.THUNDERY)
        |(wthr_cond == Weather.SHOWERS)){
            s.setText(s.getText() + "- Bring your umbrella/raincoat\n");
        }

        //for asthmatic user
        if(profile.getHealthCondition().contains(Profile.Health.ASTHMA)) {
            
            s.setText(s.getText() + "- Always keep inhaler within your reach\n");

            if (psi > 101 & psi < 150) {
                psi_txt.setText("- Value of PSI is unhealthy for sensitive group\n");
            }

            if (pm25 > 50 & pm25 < 100) {
                pm25_txt.setText("- Value of PM2.5 is unhealthy for sensitive group\n");
            }
        }
        //for skin cancer user
        if(profile.getHealthCondition().contains(Profile.Health.SKIN_CANCER)) {
            
        	if (uvi > 1)
            	s.setText(s.getText() + "- Reduce exposure to sunlight\n");

            if (uvi > 3 & uvi < 7) {                
                uvi_txt.setText("- UVI level might have risk of harm based on your health condition\n");
            }

        }
        //for normal users
        if (pm25 >= 100)
        {
            pm25_txt.setText("- Value of PM2.5 is hazardous!!\n");
        }
        if (psi >= 150)
        {
            psi_txt.setText("- Value of PSI is hazardous!!\n");
        }
        if (uvi >=7){
            uvi_txt.setText("- Value of UVI is hazardous!!\n");
        }

        if (psi >= 150 | pm25 >= 100){
            r.setText(r.getText() + "- Avoid/Reduce outdoor activities\n"
            		+ "- Wear face mask when going outdoor\n");
        }
        if (uvi >= 7){
            r.setText(r.getText() + "- Apply broad spectrum SPF 30+ sunscreen every 2 hours\n" +
                    "- If outdoors, seek shade and wear Sun protective clothing, a wide-brimmed hat, and UV-blocking sunglasses.\n");
        }

        return rcmd;
    }
}

