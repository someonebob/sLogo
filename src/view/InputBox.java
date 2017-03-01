package view;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import tool.FileTool.OpenButton;
import tool.FileTool.SaveButton;

/**
 * The view that displays the console, allows the user to input commands
 * 
 * @author Jesse
 *
 */
public class InputBox implements View
{
	private BorderPane inputBox;
	private ScrollPane scroll;
	private TextField console;
	private VBox inputs;
	private List<String> previous;
	private String input;
	private Stack<String> clickedCommands;

	/**
	 * Generates all the nodes and defines their actions
	 */
	public InputBox()
	{
		initiateItems();
		console.setOnAction(e -> consoleAction());
	}

	/**
	 * Returns the the input of the console
	 * 
	 * @return
	 */
	public String getInput()
	{
		return input;
	}
	
	public TextField getField(){
		return console;
	}

	public List<String> getPastInputs()
	{
		return previous;
	}

	/**
	 * Returns the group of nodes to be displayed
	 * 
	 * @return
	 */
	@Override
	public BorderPane display()
	{
		return inputBox;
	}

	@Override
	public void update(Observable o, Object arg)
	{
		if (o instanceof SaveButton) {
			saveFile((File) arg);
		}
		if (o instanceof OpenButton) {
			openFile((File) arg);
		}
	}

	@Override
	public void updateData(String arg)
	{
		// Does nothing since the InputBox never needs to be updated
	}

	private void initiateItems()
	{
		inputBox = new BorderPane();
		scroll = new ScrollPane();
		scroll.setPrefHeight(200);
		scroll.setMaxHeight(200);

		inputs = new VBox();
		scroll.setContent(inputs);
		scroll.vvalueProperty().bind(inputs.heightProperty());

		console = new TextField();
		console.setPromptText("Enter your code here...");
		inputBox.setCenter(scroll);
		inputBox.setBottom(console);

		previous = new ArrayList<>();
		clickedCommands = new Stack<>();
	}

	private void consoleAction()
	{
		input = console.getText();
		console.clear();
		
	}
	public void addPrevious(String prev){
		Label current = new Label(prev);
		current.setOnMouseClicked(e -> clickedCommands.add(current.getText()));
		inputs.getChildren().add(current);
	}

	private void saveFile(File file)
	{
		FileWriter fw = null;
		try {
			fw = new FileWriter(file);
			fw.write(convertPrevious());
			fw.close();
		} catch (IOException e) {
			Logger.getLogger(InputBox.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	private void openFile(File file)
	{

	}

	public Stack<String> getClickedCommands()
	{
		return clickedCommands;
	}

	private String convertPrevious()
	{
		StringBuilder content = new StringBuilder();
		for (String s : previous) {
			content.append(s + "\n");
		}
		return content.toString();
	}

}
