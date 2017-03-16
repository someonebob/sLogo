package exceptions;

/**
 * Subclass of SyntaxException thrown when error associated with Reflection is
 * encountered.
 * 
 * @author Matthew Barbano
 * @see SLogoException SLogoException for assumptions, dependencies, and
 *      examples
 */
public class ReflectionException extends SyntaxException {
	private static final long serialVersionUID = 1L;

	/**
	 * Calls SLogoException constructor with same set of parameters.
	 * 
	 * @param instructionSpecificErrorName
	 * @see SLogoException SLogoException for assumptions, dependencies, and
	 *      examples
	 */
	public ReflectionException(String instructionSpecificErrorName) {
		super(instructionSpecificErrorName);
	}

	/**
	 * Calls SLogoException constructor with same set of parameters.
	 * 
	 * @param instructionSpecificErrorName
	 * @see SLogoException SLogoException for assumptions, dependencies, and
	 *      examples
	 */
	public ReflectionException(Throwable cause) {
		super(cause);
	}

	/**
	 * Calls SLogoException constructor with same set of parameters.
	 * 
	 * @param instructionSpecificErrorName
	 * @see SLogoException SLogoException for assumptions, dependencies, and
	 *      examples
	 */
	public ReflectionException(String errorReport, Throwable cause) {
		super(errorReport, cause);
	}

}
