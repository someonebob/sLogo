package view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * 
 * @author Jesse
 *
 */
public class MultiLineInputBox implements InputBox {
	private BorderPane root;
	private VBox box;
	private TextArea console;
	private ListView<String> previous;
	private int historyIndex = 0;
	private String preamble = "slogo_team07$ ";

	@Override
	public Node display() {
		return root;
	}

	@Override
	public void updateData(String arg) {
		previous.getItems().add(arg);
	}
	private void initiateItems() {
		root = new BorderPane();
		console = new TextArea();
		Tooltip tip = new Tooltip("Use SHIFT+ENTER to type multiple lines");
		console.tooltipProperty().set(tip);
		console.setOnMouseClicked(e -> console.positionCaret(console.getText().length()));
		console.setWrapText(true);
		console.textProperty().addListener(new ChangeListener<Object>() {
		    @Override
		    public void changed(ObservableValue<?> observable, Object oldValue, Object newValue) {
		    	//scroll to bottom
		        console.setScrollTop(Double.MAX_VALUE);
		    }
		});
		box = new VBox();
		previous = new ListView<>();
		Label heading = new Label("Previous Commands");
		
		heading.setStyle("-fx-font-weight: bold");
		box.setAlignment(Pos.CENTER);
		box.getChildren().addAll(heading, previous);

		console.appendText(preamble);
		console.setFont(Font.font("Courier new"));
		previous.setPrefWidth(200);
		previous.setFocusTraversable(false);
		previous.setOnMouseClicked(e -> appendText(previous.getSelectionModel().getSelectedItem()));
		
		root.setMaxHeight(200);
		root.setLeft(box);
		root.setCenter(console);
	}
	
	@Override
	public void assignOnEnterCommand(EventHandler<? super KeyEvent> e){
		console.setOnKeyPressed(e);
	}
	@Override
	public void appendPreamble(){
		appendText("\n" + preamble);
	}
	@Override
	public String getCurrentCommand(){
		// returns text between last instance of preamble and end
		return console.getText(console.getText().lastIndexOf(preamble) + preamble.length(), console.getText().length());
	}
	@Override
	public void clear(){
		console.setText(preamble);
	}
	@Override
	public void setFocus(){
		console.requestFocus();
	}
	@Override
	public void appendText(String s){
		console.appendText(s);
	}
	@Override
	public void upAction(KeyEvent e) {
		if (historyIndex <= previous.getItems().size() - 1) {
			appendPastCommand();
		}
		if (historyIndex < previous.getItems().size() - 1) {
			historyIndex++;
		}
		e.consume();
	}
	@Override
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
	@Override
	public void protectPreamble(KeyEvent e) {
		int pos = console.getText().lastIndexOf(preamble) + preamble.length();
		if (console.getSelectedText().length() != 0 || pos == console.getCaretPosition()) {
			e.consume();
		}
	}
	@Override
	public void enterAction(KeyEvent e) {
		e.consume();
		historyIndex = 0;
	}
	public void shiftEnterAction(KeyEvent e){
		e.consume();
		console.appendText("\n");;
	}
	


	public MultiLineInputBox() {
		initiateItems();
	}



	public String returnAllText() {
		StringBuilder content = new StringBuilder();
		for (String s : previous.getItems()) {
			content.append(s + "\n");
		}
		content.delete(content.length()-1, content.length());
		return content.toString();
	}

}
