package Boundary;

import Error.RequestError;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import org.json.JSONException;
import org.openjfx.ce2006project_maven.App;


import java.io.IOException;


/**
 * The scene displayed when user presses the Get route button in the mainUI
 * Used to display route information, weather information, warnings and recommendations
 * Switch to mainUI scene when back button is pressed
 */
public class routeUI {

	/**
	 * WebEngine for search of route using google map
	 */
	public static WebEngine webengine;
	/**
	 * WebView to display the google map
	 */
	public static WebView webview;

	/**
	 * A function to initialise the route scene
	 * @param stage Current stage
	 * @return A scene of routeUI
	 */
	public static Scene create(Stage stage)
	{
    	//Route Scene Element Initialization	
    	webview = new WebView();
    	webengine = webview.getEngine();
        webengine.getLoadWorker().stateProperty().addListener(
        		new ChangeListener<State>() {
        			@SuppressWarnings("rawtypes")
					public void changed(ObservableValue ov, State oldState, State newState) {
        				if (newState == State.SUCCEEDED) {
        					webengine.executeScript("addressInput(\"" + mainUI.start.getName() + "\", \"" + mainUI.end.getName() + "\")");
        				}
        			}
        		}
        		);
    	Button backButton2 = new Button("Back");
    	HBox backHBox2 = new HBox(10);
    	AnchorPane routePane = new AnchorPane();
    	HBox warningHBox = new HBox();
    	ScrollPane reccBox = new ScrollPane();
    	
    	//Route Scene
        
        //Scene Element Properties
    	webview.setVisible(true);
    	webengine.setJavaScriptEnabled(true);
    	webengine.load(App.class.getResource("/googlemaps2.html").toExternalForm());
    	backHBox2.getChildren().add(backButton2);
    	backHBox2.setPadding(new Insets(10));
    	AnchorPane.setLeftAnchor(backHBox2, 0.0);
    	String style = "-fx-background-color: rgba(255, 255, 230, 1);";
    	String border_style = 
    			"-fx-padding: 10;" + "-fx-border-style: solid inside;"
                + "-fx-border-width: 2;" + "-fx-border-insets: 5;"
                + "-fx-border-radius: 5;" + "-fx-border-color: black;";
    	warningHBox.setStyle(style);
    	reccBox.setStyle(style + border_style);
    	reccBox.setPrefWidth(300);
    	reccBox.setPrefHeight(150);
    	reccBox.setMaxWidth(300);
    	reccBox.setMaxHeight(150);
    	reccBox.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    	reccBox.setPadding(new Insets(10));
    	AnchorPane.setLeftAnchor(warningHBox, 10.0);
    	AnchorPane.setBottomAnchor(warningHBox, 10.0);
    	AnchorPane.setTopAnchor(reccBox, 0.0);
    	AnchorPane.setRightAnchor(reccBox, 0.0);
    	
    	AnchorPane.setLeftAnchor(webview, 0.0);
    	AnchorPane.setBottomAnchor(webview, 0.0);
    	AnchorPane.setTopAnchor(webview, 0.0);
    	AnchorPane.setRightAnchor(webview, 0.0);
        
    	//Adding elements to grids
        routePane.getChildren().add(webview);
        routePane.getChildren().add(backHBox2);
        routePane.getChildren().add(warningHBox);
        routePane.getChildren().add(reccBox);
               
        
        //Element Logics
        backButton2.setOnAction(e -> stage.setScene(mainUI.create(stage)));
        //warningMessage.setWrapText(true);
        //reccMessage.setWrapText(true);
		try {
//			//Testing purpose
//			WeatherInfo[] wthr = new WeatherInfo[2];
//			wthr[0] = new WeatherInfo(120,170,44,"RAIN", WeatherInfo.Weather.RAIN);
//			wthr[1] = new WeatherInfo(1,2,3,"FAIR", WeatherInfo.Weather.FAIR);
//			ShowWeatherInfo show_wthr = new ShowWeatherInfo(wthr);
			ShowWeatherInfo show_wthr = new ShowWeatherInfo(mainUI.start, mainUI.end);
			warningHBox.getChildren().addAll(show_wthr.weatherMsg());
			TextFlow reccMessage = show_wthr.reccMsg();
			reccMessage.setLineSpacing(1.5);
			reccMessage.setTextAlignment(TextAlignment.JUSTIFY);
			reccMessage.setStyle(style);
			reccMessage.setPrefWidth(reccBox.getMaxWidth() - 48);
			reccMessage.setPrefHeight(reccBox.getMaxHeight());
			reccMessage.getChildren().forEach(e -> {
				Text t = (Text)e;
				t.setWrappingWidth(reccMessage.getMaxWidth());
				t.setFont(new Font(13));
			});
			
			Text recc = new Text();
	        recc.setText("Recommendation\n");
	        recc.setFont(Font.font("calibri", FontWeight.BOLD, FontPosture.REGULAR, 20));
	        recc.setLineSpacing(2);
	        reccMessage.getChildren().add(0, recc);
	    	reccBox.setContent(reccMessage);
		}
		catch (IOException | JSONException | RequestError e) {
			e.printStackTrace();
		}
		
    	return new Scene(routePane);
	}
}
