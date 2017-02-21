package errors;

/**
 * Deals with all things errors
 * @author Jesse
 *
 */
public interface ErrorHandler {
	
	/**
	 * Shows an Alert to the user with the given message
	 */
	public void showError(String input);
}
