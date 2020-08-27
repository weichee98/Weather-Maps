package Boundary;

import Control.WarningLogic;
import Control.WeatherManager;
import Entity.Location;
import Entity.WeatherInfo;
import Error.RequestError;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;


/**
 * A class used to format the style of the weather information text and recommendation text to be displayed on routeUI
 */
public class ShowWeatherInfo {

    /**
     * An array of WeatherInfo containing weather info of start and destination
     */
    private WeatherInfo[] weather;

    /**
     * A function to obtain the weather info of start and destination
     * @param start	The starting location
     * @param end	The ending location

     */
    public ShowWeatherInfo(Location start, Location end) throws IOException, RequestError {
        weather = WeatherManager.getInstance().requestWeatherInfo(start, end);
    }

    /**
     * A function to organise and get the Warning/weather messages
     * @return A list of TextfFlow containing Warning/weather messages for Start and Destination
     */
    public List<TextFlow> weatherMsg() throws IOException {

        Text start = new Text();
        Text end = new Text();
        String border_style = 
    			"-fx-padding: 10;" + "-fx-border-style: solid inside;"
                + "-fx-border-width: 2;" + "-fx-border-insets: 5;"
                + "-fx-border-radius: 5;" + "-fx-border-color: black;";

        WarningLogic wl_start = new WarningLogic(this.weather[0]);
        TextFlow warning_Start = wl_start.getWarning();
        warning_Start.setLineSpacing(1.5);
        warning_Start.getChildren().forEach(e -> {
			Text t = (Text)e;
			t.setFont(new Font(13));
		});
        warning_Start.setStyle(border_style);
        start.setText("Starting Location\n");
        start.setFont(Font.font("calibri", FontWeight.BOLD, FontPosture.REGULAR, 20));
        start.setLineSpacing(2);
        warning_Start.getChildren().add(0, start);        

        WarningLogic wl_end = new WarningLogic(this.weather[1]);
        TextFlow warning_End = wl_end.getWarning();
        warning_End.setLineSpacing(1.5);
        warning_End.getChildren().forEach(e -> {
			Text t = (Text)e;
			t.setFont(new Font(13));
		});
        warning_End.setStyle(border_style);        
        end.setText("Destination\n");
        end.setFont(Font.font("calibri", FontWeight.BOLD, FontPosture.REGULAR, 20));
        end.setLineSpacing(2);
        warning_End.getChildren().add(0, end);
       
        

        return Arrays.asList(warning_Start, warning_End);
        }

    /**
     * A function to organise and get the recommendation messages
     * @return A TextFlow object containing the recommendation
     */
    public TextFlow reccMsg() throws IOException {
        WeatherInfo wthr = WeatherManager.getInstance().extract_wthr(this.weather);
        WarningLogic rc = new WarningLogic(wthr);
        return rc.recommendation();
    }

//     Debugging and testing
//    public ShowWeatherInfo(WeatherInfo[] weather){
//        this.weather = weather;
//    }

}



