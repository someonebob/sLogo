package exceptions;

import java.util.ResourceBundle;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * This exception class generates custom exceptions to the SLogo project. It is
 * at the root of an inheritance hierarchy representing all possible exceptions
 * thrown during this project.
 * 
 * @author Matthew Barbano
 *
 */
public class SLogoException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private static final String RESOURCES_NAME = "resources/languages/Exception";
	private static final String EXCEPTION_NAME = "Exception";
	private static final String PLEASE_NAME = "PleasePrompt";

	private ResourceBundle resources;
	private String instructionSpecificText;

	// This one is the preferred constructor to use
	public SLogoException(String instructionSpecificErrorName) {
		super(ResourceBundle.getBundle(RESOURCES_NAME).getString(instructionSpecificErrorName));
		resources = ResourceBundle.getBundle(RESOURCES_NAME);
		instructionSpecificText = resources.getString(instructionSpecificErrorName);
	}

	public SLogoException(Throwable cause) {
		super(cause);
		resources = ResourceBundle.getBundle(RESOURCES_NAME);
		instructionSpecificText = "";
	}

	public SLogoException(String errorReport, Throwable cause) {
		super(errorReport, cause);
		resources = ResourceBundle.getBundle(RESOURCES_NAME);
		instructionSpecificText = "";
	}

	/**
	 * Called when an exception is caught in a try-catch block. Displays an
	 * Alert with information about the exception.
	 */
	public void displayAlert() {
		String displayMessage = resources.getString(EXCEPTION_NAME) + ": " + instructionSpecificText + "\n"
				+ resources.getString(PLEASE_NAME);
		Alert alert = new Alert(AlertType.ERROR, displayMessage);
		alert.showAndWait();
	}
}
