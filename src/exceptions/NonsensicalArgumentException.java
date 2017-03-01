package exceptions;

public class NonsensicalArgumentException extends ExecuteException{
	private static final long serialVersionUID = 1L;

	public NonsensicalArgumentException(String instructionSpecificErrorName){
		super(instructionSpecificErrorName);
	}
	
	public NonsensicalArgumentException(Throwable cause){
		super(cause);
	}
	
	public NonsensicalArgumentException(String errorReport, Throwable cause){
		super(errorReport, cause);
	}

}
