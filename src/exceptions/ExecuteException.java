package exceptions;

/**
 * This class of exceptions is thrown when a valid Instruction is generated
 * but cannot be executed properly. Examples include dividing by zero and 
 * having arguments that are invalid indices. It is thrown within an Instruction
 * method and caught elsewhere, when an Alert window is displayed to the user.
 * 
 * @author Matthew Barbano
 * @see SLogoException SLogoException for assumptions, dependencies, and examples
 */
public class ExecuteException extends SLogoException{
	private static final long serialVersionUID = 1L;

	/**
	 * Calls SLogoException constructor with same set of parameters.
	 * @param instructionSpecificErrorName
	 * @see SLogoException SLogoException for assumptions, dependencies, and examples
	 */
	public ExecuteException(String instructionSpecificErrorName){
		super(instructionSpecificErrorName);
	}
	
	/**
	 * Calls SLogoException constructor with same set of parameters.
	 * @param instructionSpecificErrorName
	 * @see SLogoException SLogoException for assumptions, dependencies, and examples
	 */
	public ExecuteException(Throwable cause){
		super(cause);
	}
	
	/**
	 * Calls SLogoException constructor with same set of parameters.
	 * @param instructionSpecificErrorName
	 * @see SLogoException SLogoException for assumptions, dependencies, and examples
	 */
	public ExecuteException(String errorReport, Throwable cause){
		super(errorReport, cause);
	}
}
