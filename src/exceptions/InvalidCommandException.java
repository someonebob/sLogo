package exceptions;

/**
 * A subclass of SyntaxException thrown when the spelling of a
 * command is not recognized.
 * @author Matthew Barbano
 *
 */
public class InvalidCommandException extends SyntaxException{
	private static final long serialVersionUID = 1L;
	
	public InvalidCommandException(String instructionSpecificErrorName){
		super(instructionSpecificErrorName);
	}
	
	public InvalidCommandException(Throwable cause){
		super(cause);
	}
	
	public InvalidCommandException(String errorReport, Throwable cause){
		super(errorReport, cause);
	}

}
