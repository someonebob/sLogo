package exceptions;

public class SLogoException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public SLogoException(String errorReport){
		super(errorReport);
	}
	
	public SLogoException(Throwable cause){
		super(cause);
	}
	
	public SLogoException(String errorReport, Throwable cause){
		super(errorReport, cause);
	}
}
