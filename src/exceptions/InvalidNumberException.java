package exceptions;

public class InvalidNumberException extends SLogoException{

	private static final long serialVersionUID = 1L;

	public InvalidNumberException(String instructionSpecificErrorName) {
		super(instructionSpecificErrorName);
	}

}
