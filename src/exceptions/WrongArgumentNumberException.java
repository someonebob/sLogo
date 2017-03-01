package exceptions;

public class WrongArgumentNumberException extends SyntaxException{
	private static final long serialVersionUID = 1L;
	
	public WrongArgumentNumberException(String instructionSpecificErrorName){
		super(instructionSpecificErrorName);
	}
	
	public WrongArgumentNumberException(Throwable cause){
		super(cause);
	}
	
	public WrongArgumentNumberException(String errorReport, Throwable cause){
		super(errorReport, cause);
	}

}
