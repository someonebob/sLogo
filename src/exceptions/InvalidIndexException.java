package exceptions;

/**
 * This exception is thrown when an invalid index (i.e. noninteger or out of
 * bounds) is used in a command.
 * 
 * @author Matthew Barbano
 * @see SLogoException SLogoException for assumptions, dependencies, and
 *      examples
 */
public class InvalidIndexException extends ExecuteException {
	private static final long serialVersionUID = 1L;

	/**
	 * Calls SLogoException constructor with same set of parameters.
	 * 
	 * @param instructionSpecificErrorName
	 * @see SLogoException SLogoException for assumptions, dependencies, and
	 *      examples
	 */
	public InvalidIndexException(String instructionSpecificErrorName) {
		super(instructionSpecificErrorName);
	}

	/**
	 * Calls SLogoException constructor with same set of parameters.
	 * 
	 * @param instructionSpecificErrorName
	 * @see SLogoException SLogoException for assumptions, dependencies, and
	 *      examples
	 */
	public InvalidIndexException(Throwable cause) {
		super(cause);
	}

	/**
	 * Calls SLogoException constructor with same set of parameters.
	 * 
	 * @param instructionSpecificErrorName
	 * @see SLogoException SLogoException for assumptions, dependencies, and
	 *      examples
	 */
	public InvalidIndexException(String errorReport, Throwable cause) {
		super(errorReport, cause);
	}
}