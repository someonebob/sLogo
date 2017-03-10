package exceptions;

public class ObserverException extends SLogoException{

	private static final long serialVersionUID = 1L;

	public ObserverException(String instructionSpecificErrorName) {
		super(instructionSpecificErrorName);
	}

}
