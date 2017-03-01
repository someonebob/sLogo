package exceptions;

import java.util.ResourceBundle;

/**
 * This exception class generates custom exceptions to the SLogo project. It is
 * at the root of an inheritance hierarchy representing all possible exceptions
 * thrown during this project.
 * @author Matthew Barbano
 *
 */
public class SLogoException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	private static final String RESOURCES_NAME = "resources/languages/Exception";
	
	private ResourceBundle resources;
	private String instructionSpecificText;
	
	//This one is the preferred constructor to use
	public SLogoException(String instructionSpecificErrorName){
		//super(ResourceBundle.getBundle(RESOURCES_NAME).getString(instructionSpecificErrorName));
		resources = ResourceBundle.getBundle(RESOURCES_NAME);
		instructionSpecificText = resources.getString(instructionSpecificErrorName);
	}
	
	public SLogoException(Throwable cause){
		super(cause);
		resources = ResourceBundle.getBundle(RESOURCES_NAME);
		instructionSpecificText = "";
	}
	
	public SLogoException(String errorReport, Throwable cause){
		super(errorReport, cause);
		resources = ResourceBundle.getBundle(RESOURCES_NAME);
		instructionSpecificText = "";
	}

	public void displayAlert(){
		//TODO
		System.out.println("This message will be replaced by a popup error window eventually.");
	}
}
