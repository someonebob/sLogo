package exceptions;

import java.util.ResourceBundle;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * <p>
 * This exception class generates custom exceptions to the SLogo project. It is
 * at the root of an inheritance hierarchy representing all possible exceptions
 * thrown during this project. It extends Java's RuntimeException (so it
 * emulates a RuntimeException's behavior) and includes code for displaying a
 * popup window when a SLogo exception is caught. It is assumed that all Strings
 * corresponding to messages in "Exception.properties" do exist in that file,
 * and that the file is located at resources/languages/Exception. Dependencies
 * are String, Alert, RuntimeException, ResourceBundle, and
 * Exception.properties. Example of use:
 * </p>
 * 
 * <p>
 * In an Instruction subclass:
 * </p>
 * 
 * <pre>
 * private static final String RESOURCE_ERROR_TYPE_NAME = "Corresponds to message in Exception.properties";
 * 
 * public double execute() {
 * 	if (conditionMet) {
 * 		throw new MathException(RESOURCE_ERROR_TYPE_NAME);
 * 	}
 * }
 * </pre>
 * 
 * <p>
 * In Controller's runCommand():
 * </p>
 * 
 * <pre>
 * try {
 * 	Interpreter interpreter = new Interpreter(data);
 * 	printValue = interpreter.parseAndRun(command);
 * 	// Other code here
 * } catch (SLogoException exception) {
 * 	exception.displayAlert();
 * }
 * </pre>
 * 
 * Some code taken from/inspired by Prof. Duvall's XMLException class in
 * example_xml.
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

	/**
	 * This is the preferred constructor to use. Instantiates a SLogoException
	 * by calling Java's RuntimeException constructor with the error message
	 * titled with the String "instructionSpecificErrorName" in
	 * Exception.properties. Then sets the resources and instructionSpecificText
	 * appropriately. Assumes that the parameter corresponds to a message title
	 * in Exception.properties. Dependencies are String and ResourceBundle.
	 * 
	 * @param instructionSpecificErrorName
	 *            the title of the error message in Exception.properties
	 */
	public SLogoException(String instructionSpecificErrorName) {
		super(ResourceBundle.getBundle(RESOURCES_NAME).getString(instructionSpecificErrorName));
		resources = ResourceBundle.getBundle(RESOURCES_NAME);
		instructionSpecificText = resources.getString(instructionSpecificErrorName);
	}

	/**
	 * Calls Java's RuntimeException constructor with "cause", instantiates
	 * "resources" appropriately, and "instructionSpecificText" to a blank
	 * String. No major assumptions; dependencies are String and ResourceBundle.
	 * 
	 * @param cause
	 *            of the exception
	 */
	public SLogoException(Throwable cause) {
		super(cause);
		resources = ResourceBundle.getBundle(RESOURCES_NAME);
		instructionSpecificText = "";
	}

	/**
	 * Calls Java's RuntimeException constructor with errorReport and cause,
	 * instantiates "resources" appropriately, and "instructionSpecificText" to
	 * a blank String. No major assumptions; dependencies are String and
	 * ResourceBundle.
	 * 
	 * @param errorReport
	 *            to call RuntimeException's constructor with
	 * @param cause
	 *            of the exception
	 */
	public SLogoException(String errorReport, Throwable cause) {
		super(errorReport, cause);
		resources = ResourceBundle.getBundle(RESOURCES_NAME);
		instructionSpecificText = "";
	}

	/**
	 * Called when a SLogoException is caught in a try-catch block. Displays an
	 * Alert with information about the exception, including the message in
	 * Exception.properties specified in the SLogoException constructor. Assumes
	 * all Strings correspond to existing messages in resource files.
	 * Dependencies are String, Alert, and ResourceBundle.
	 */
	public void displayAlert() {
		String displayMessage = resources.getString(EXCEPTION_NAME) + ": " + instructionSpecificText + "\n"
				+ resources.getString(PLEASE_NAME);
		Alert alert = new Alert(AlertType.ERROR, displayMessage);
		alert.showAndWait();
	}
}
