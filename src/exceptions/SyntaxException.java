package exceptions;

/**
 * This exception class is for problems related to the parsing 
 * of the text a user inputs and is thrown when this text cannot
 * be properly translated into a recognized command. It should be
 * thrown and caught in the Interpreter class before any Instructions are
 * created, since no valid Instruction could be created from some 
 * command when a SyntaxException is thrown.
 * 
 * @author Matthew Barbano
 * @see SLogoException SLogoException for assumptions, dependencies, and examples
 */

public class SyntaxException extends SLogoException{
	private static final long serialVersionUID = 1L;
	
	/**
	 * Calls SLogoException constructor with same set of parameters.
	 * @param instructionSpecificErrorName
	 * @see SLogoException SLogoException for assumptions, dependencies, and examples
	 */
	public SyntaxException(String instructionSpecificErrorName){
		super(instructionSpecificErrorName);
	}
	
	/**
	 * Calls SLogoException constructor with same set of parameters.
	 * @param instructionSpecificErrorName
	 * @see SLogoException SLogoException for assumptions, dependencies, and examples
	 */
	public SyntaxException(Throwable cause){
		super(cause);
	}
	
	/**
	 * Calls SLogoException constructor with same set of parameters.
	 * @param instructionSpecificErrorName
	 * @see SLogoException SLogoException for assumptions, dependencies, and examples
	 */
	public SyntaxException(String errorReport, Throwable cause){
		super(errorReport, cause);
	}

}
