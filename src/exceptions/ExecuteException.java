package exceptions;

/**
 * This class of exceptions is thrown when a valid Instruction is generated
 * but cannot be executed properly. Examples include dividing by zero and 
 * having out-of-range arguments. It is thrown within an Instruction
 * method and caught elsewhere, when an Alert window is displayed to the user.
 * @author Matthew Barbano
 *
 */
public class ExecuteException extends SLogoException{
	private static final long serialVersionUID = 1L;

	public ExecuteException(String instructionSpecificErrorName){
		super(instructionSpecificErrorName);
	}
	
	public ExecuteException(Throwable cause){
		super(cause);
	}
	
	public ExecuteException(String errorReport, Throwable cause){
		super(errorReport, cause);
	}
}
