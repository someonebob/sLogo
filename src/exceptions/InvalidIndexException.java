package exceptions;

/**
 * This exception is thrown when an invalid index (i.e. noninteger
 * or out of bounds) is used in a command.
 * @author Matthew Barbano
 *
 */
public class InvalidIndexException extends ExecuteException{
	private static final long serialVersionUID = 1L;

	public InvalidIndexException(String instructionSpecificErrorName){
		super(instructionSpecificErrorName);
	}
	
	public InvalidIndexException(Throwable cause){
		super(cause);
	}
	
	public InvalidIndexException(String errorReport, Throwable cause){
		super(errorReport, cause);
	}

}
