package Tools;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * The class used to display alert message box
 */
public class ErrorAlert {

	/**
	 * The method used to display alert message box
	 * @param arg	The string to be displayed
	 */
	public static void errorAlert(String arg) {
		
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setContentText(arg);
		alert.showAndWait();
		
	}
	
}
