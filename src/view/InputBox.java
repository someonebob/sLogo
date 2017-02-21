package view;
/**
 * The view that displays the console, allows the user to input commands
 * @author Jesse
 *
 */
public interface InputBox extends PageView{
	/**
	 * Returns the the input of the console
	 * @return
	 */
	public String getInput();
}
