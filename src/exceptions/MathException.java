package exceptions;

/**
 * Subclass of ExecuteException thrown when math-based errors are encountered
 * (such as divide by zero).
 * 
 * @author Matthew Barbano
 * @see SLogoException SLogoException for assumptions, dependencies, and
 *      examples
 */
public class MathException extends ExecuteException {
	private static final long serialVersionUID = 1L;

	/**
	 * Calls SLogoException constructor with same set of parameters.
	 * 
	 * @param instructionSpecificErrorName
	 * @see SLogoException SLogoException for assumptions, dependencies, and
	 *      examples
	 */
	public MathException(String instructionSpecificErrorName) {
		super(instructionSpecificErrorName);
	}

	/**
	 * Calls SLogoException constructor with same set of parameters.
	 * 
	 * @param instructionSpecificErrorName
	 * @see SLogoException SLogoException for assumptions, dependencies, and
	 *      examples
	 */
	public MathException(Throwable cause) {
		super(cause);
	}

	/**
	 * Calls SLogoException constructor with same set of parameters.
	 * 
	 * @param instructionSpecificErrorName
	 * @see SLogoException SLogoException for assumptions, dependencies, and
	 *      examples
	 */
	public MathException(String errorReport, Throwable cause) {
		super(errorReport, cause);
	}

}
