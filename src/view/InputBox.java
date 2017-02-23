package view;

import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * The view that displays the console, allows the user to input commands
 * @author Jesse
 *
 */
public class InputBox {
	private BorderPane inputBox;
	private ScrollPane scroll;
	private TextField console;
	private String input;
	private VBox inputs;
	
	public InputBox(){
		initiateItems();
		console.setOnAction(e -> consoleAction());
	}
	
	/**
	 * Returns the the input of the console
	 * @return
	 */
	public String getInput(){
		return input;
	}
	
	public BorderPane display(){
		return inputBox;
	}
	
	private void initiateItems(){
		inputBox = new BorderPane();
		scroll = new ScrollPane();
		
		inputs = new VBox();
		scroll.setContent(inputs);
		
		console = new TextField();
		console.setPromptText("Enter your code here...");
		inputBox.setCenter(scroll);
		inputBox.setBottom(console);
	}
	
	private void consoleAction(){
		input = console.getText();
		console.clear();
		Text current = new Text(input);
		inputs.getChildren().add(current);
	}

}
