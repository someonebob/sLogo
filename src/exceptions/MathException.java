package exceptions;

/**
 * Subclass of ExecuteException thrown when math-based errors
 * are encountered (such as divide by zero).
 * @author Matthew Barbano
 *
 */
public class MathException extends ExecuteException{
	private static final long serialVersionUID = 1L;

	public MathException(String instructionSpecificErrorName){
		super(instructionSpecificErrorName);
	}
	
	public MathException(Throwable cause){
		super(cause);
	}
	
	public MathException(String errorReport, Throwable cause){
		super(errorReport, cause);
	}

}
