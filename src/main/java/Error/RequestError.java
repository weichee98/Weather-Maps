package Error;

import Tools.ErrorAlert;


/**
 * An exception class, thrown when classes failed to request information from APIs
 * An alert message box will appear when this error is thrown
 */
@SuppressWarnings("serial")
public class RequestError extends Exception {

	/**
	 * The default constructor
	 */
	public RequestError() {
	}

	/**
	 * A function to catch error in getting result from API
	 * @param arg0 String to be displayed in alert message box when error occurs
	 */
	public RequestError(String arg0) {
		super(arg0);
		ErrorAlert.errorAlert(arg0);
	}

}
