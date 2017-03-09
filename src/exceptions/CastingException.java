package exceptions;

/**
 * Thrown when a casting problem is encountered.
 * @author Matthew Barbano
 *
 */
public class CastingException extends ExecuteException{
	private static final long serialVersionUID = 1L;
	
	public CastingException(String instructionSpecificErrorName){
		super(instructionSpecificErrorName);
	}
	
	public CastingException(Throwable cause){
		super(cause);
	}
	
	public CastingException(String errorReport, Throwable cause){
		super(errorReport, cause);
	}

}
