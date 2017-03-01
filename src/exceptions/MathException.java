package exceptions;

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
