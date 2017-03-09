package view;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public interface InputBox extends View {

	public void enterAction(KeyEvent e);
	public void assignOnEnterCommand(EventHandler<? super KeyEvent> e);
	public void appendPreamble();
	public String getCurrentCommand();
	public void clear();
	public void setFocus();
	public void appendText(String s);
	public void upAction(KeyEvent e);
	public void downAction(KeyEvent e);
	public void protectPreamble(KeyEvent e);
	public void updateData(String arg);
}