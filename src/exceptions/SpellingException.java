package exceptions;

public class SpellingException extends SyntaxException{
	private static final long serialVersionUID = 1L;
	
	public SpellingException(String instructionSpecificErrorName){
		super(instructionSpecificErrorName);
	}
	
	public SpellingException(Throwable cause){
		super(cause);
	}
	
	public SpellingException(String errorReport, Throwable cause){
		super(errorReport, cause);
	}

}
