package view;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import tool.FileTool.SaveButton;

public class InputBox implements View {
	private BorderPane root;
	private TextArea console;
	private VBox box;
	private ListView<String> previous;
	private int historyIndex = 0;
	private String preamble = "slogo_team07$ ";

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		if (o instanceof SaveButton) {
			saveFile((File) arg);
		}
	}

	@Override
	public Node display() {
		// TODO Auto-generated method stub
		return root;
	}

	@Override
	public void updateData(String arg) {
		previous.getItems().add(arg);
	}


	public TextArea getConsole() {
		return console;
	}
	
	public ListView<String> getPrevious() {
		return previous;
	}
	
	public String getPreamble() {
		return preamble;
	}

	public String getCurrentCommand() {
		// returns text between last instance of preamble and end
		return console.getText(console.getText().lastIndexOf(preamble) + preamble.length(), console.getText().length());
	}

	public void clear() {
		console.setText(preamble);
	}
	
	public void setFocus(){
		console.requestFocus();
	}
	
	public void enterAction(KeyEvent e) {
		e.consume();
		historyIndex = 0;
	}
	public void upAction(KeyEvent e) {
		if (historyIndex <= previous.getItems().size() - 1) {
			append();
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
		append();
		e.consume();
	}

	public void protectPreamble(KeyEvent e) {
		int pos = console.getText().lastIndexOf(preamble) + preamble.length();
		if (console.getSelectedText().length() != 0 || pos == console.getCaretPosition()) {
			e.consume();
		}
	}
	private void append() {
		clearCommand();
		console.appendText(previous.getItems().get(previous.getItems().size() - 1 - historyIndex));
	}

	public InputBox() {
		initiateItems();
	}

	private void initiateItems() {
		root = new BorderPane();
		console = new TextArea();
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
		root.setMaxHeight(200);

		root.setLeft(box);
		root.setCenter(console);
	}

	private void clearCommand() {
		console.setText(console.getText().substring(0, console.getText().lastIndexOf(getCurrentCommand())));
		console.positionCaret(console.getText().length());
	}

	private void saveFile(File file) {
		FileWriter fw = null;
		try {
			fw = new FileWriter(file);
			fw.write(convertPrevious());
			fw.close();
		} catch (IOException e) {
			Logger.getLogger(InputBox.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	private String convertPrevious() {
		StringBuilder content = new StringBuilder();
		for (String s : previous.getItems()) {
			content.append(s + "\n");
		}
		content.delete(content.length()-1, content.length());
		return content.toString();
	}

}
