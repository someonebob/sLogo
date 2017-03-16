package exceptions;

/** 
 * Thrown in VariableEditor when trying to create a variable in a nonnumerical
 * or invalid value.
 * 
 * @author jesse
 * @see SLogoException SLogoException for assumptions, dependencies, and examples
 */
public class InvalidNumberException extends SLogoException{

	private static final long serialVersionUID = 1L;

	/**
	 * Calls SLogoException constructor with same set of parameters.
	 * @param instructionSpecificErrorName
	 * @see SLogoException SLogoException for assumptions, dependencies, and examples
	 */
	public InvalidNumberException(String instructionSpecificErrorName) {
		super(instructionSpecificErrorName);
	}

}
