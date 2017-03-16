package exceptions;

/**
 * Subclass of ExecuteException thrown when command is encountered with
 * inappropriately-valued arguments (such as a negative argument for forward).
 * 
 * @author Matthew Barbano
 * @see SLogoException SLogoException for assumptions, dependencies, and
 *      examples
 */
public class NonsensicalArgumentException extends ExecuteException {
	private static final long serialVersionUID = 1L;

	/**
	 * Calls SLogoException constructor with same set of parameters.
	 * 
	 * @param instructionSpecificErrorName
	 * @see SLogoException SLogoException for assumptions, dependencies, and
	 *      examples
	 */
	public NonsensicalArgumentException(String instructionSpecificErrorName) {
		super(instructionSpecificErrorName);
	}

	/**
	 * Calls SLogoException constructor with same set of parameters.
	 * 
	 * @param instructionSpecificErrorName
	 * @see SLogoException SLogoException for assumptions, dependencies, and
	 *      examples
	 */
	public NonsensicalArgumentException(Throwable cause) {
		super(cause);
	}

	/**
	 * Calls SLogoException constructor with same set of parameters.
	 * 
	 * @param instructionSpecificErrorName
	 * @see SLogoException SLogoException for assumptions, dependencies, and
	 *      examples
	 */
	public NonsensicalArgumentException(String errorReport, Throwable cause) {
		super(errorReport, cause);
	}

}
