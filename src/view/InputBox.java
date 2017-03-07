package view;

import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;

public abstract class InputBox implements View {
	private TextArea console;
	private ListView<String> previous;
	private int historyIndex = 0;
	private String preamble = "slogo_team07$ ";
	
	
	public void assignOnEnterCommand(EventHandler<? super KeyEvent> e){
		console.setOnKeyPressed(e);
	}
	public void appendPreamble(){
		appendText("\n" + preamble);
	}
	public String getCurrentCommand(){
		// returns text between last instance of preamble and end
		return console.getText(console.getText().lastIndexOf(preamble) + preamble.length(), console.getText().length());
	}
	public void clear(){
		console.setText(preamble);
	}
	public void setFocus(){
		console.requestFocus();
	}
	public void appendText(String s){
		console.appendText(s);
	}
	public abstract void enterAction(KeyEvent e);
	public void upAction(KeyEvent e) {
		if (historyIndex <= previous.getItems().size() - 1) {
			appendPastCommand();
		}
		if (historyIndex < previous.getItems().size() - 1) {
			historyIndex++;
		}
		e.consume();
	}
	public void downAction(KeyEvent e) {
		if (historyIndex == 0) {
			clearCommand();
			return;
		}
		historyIndex--;
		appendPastCommand();
		e.consume();
	}
	protected void appendPastCommand() {
		clearCommand();
		console.appendText(previous.getItems().get(previous.getItems().size() - 1 - historyIndex));
	}
	protected void clearCommand() {
		console.setText(console.getText().substring(0, console.getText().lastIndexOf(getCurrentCommand())));
		console.positionCaret(console.getText().length());
	}
	public void protectPreamble(KeyEvent e) {
		int pos = console.getText().lastIndexOf(preamble) + preamble.length();
		if (console.getSelectedText().length() != 0 || pos == console.getCaretPosition()) {
			e.consume();
		}
	}
	
}
