package exceptions;

/**
 * A subclass of SyntaxException thrown when too few arguments are provided for
 * a command. Typically, providing too many is not an error: example, fd 50 60
 * parses to 50 60, which is left as an acceptable expression.
 * 
 * @author Matthew Barbano
 * @see SLogoException SLogoException for assumptions, dependencies, and
 *      examples
 */
public class WrongArgumentNumberException extends SyntaxException {
	private static final long serialVersionUID = 1L;

	/**
	 * Calls SLogoException constructor with same set of parameters.
	 * 
	 * @param instructionSpecificErrorName
	 * @see SLogoException SLogoException for assumptions, dependencies, and
	 *      examples
	 */
	public WrongArgumentNumberException(String instructionSpecificErrorName) {
		super(instructionSpecificErrorName);
	}

	/**
	 * Calls SLogoException constructor with same set of parameters.
	 * 
	 * @param instructionSpecificErrorName
	 * @see SLogoException SLogoException for assumptions, dependencies, and
	 *      examples
	 */
	public WrongArgumentNumberException(Throwable cause) {
		super(cause);
	}

	/**
	 * Calls SLogoException constructor with same set of parameters.
	 * 
	 * @param instructionSpecificErrorName
	 * @see SLogoException SLogoException for assumptions, dependencies, and
	 *      examples
	 */
	public WrongArgumentNumberException(String errorReport, Throwable cause) {
		super(errorReport, cause);
	}

}
