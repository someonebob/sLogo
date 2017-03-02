package exceptions;

public class ReflectionException extends SyntaxException{
	private static final long serialVersionUID = 1L;
	
	public ReflectionException(String instructionSpecificErrorName){
		super(instructionSpecificErrorName);
	}
	
	public ReflectionException(Throwable cause){
		super(cause);
	}
	
	public ReflectionException(String errorReport, Throwable cause){
		super(errorReport, cause);
	}

}
