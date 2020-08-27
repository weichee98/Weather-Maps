package org.openjfx.ce2006project_maven;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import Boundary.mainUI;

/**
 * The main app class, launched upon starting application
 */
public class App extends Application{

	Scene scene1, scene2, scene3; 
	
    /**
     * The method used to start a scene
     */
    @Override
    public void start(@SuppressWarnings("exports") Stage primaryStage) throws Exception {
    	primaryStage.setTitle("CE2006 Application");  	
    	primaryStage.setScene(mainUI.create(primaryStage));
    	primaryStage.show();
    }

    /**
     * The main function
     * @param args	Arguments for main function
     */
    public static void main(String[] args) {
        launch(args);
    }
}