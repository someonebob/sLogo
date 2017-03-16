package tool;

import java.util.List;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import user_structures.FunctionData;

public class FunctionEditor {
	private Stage window;
	private GridPane grid;
	private TextField nameEntry;
	private TextArea functionEntry;
	private Button save;
	private FunctionData changedFunction;
	
	public FunctionEditor(FunctionData data){
		initiateItems(data.getName(), data.getCommands(), data.getArgs());
		save.setOnAction(e -> buttonAction());
	}
	
	public FunctionData getChangedFunction(){
		return changedFunction;
	}
	
	private void initiateItems(String defaultName, String defaultFunction, List<String> args){
		window = new Stage();
		grid = new GridPane();
		
		changedFunction = new FunctionData(defaultName, defaultFunction, args);
		Label name = new Label("Command Name");
		Label function = new Label("Command Value");
		
		nameEntry = new TextField();
		nameEntry.setPromptText(defaultName);
		
		functionEntry = new TextArea();
		functionEntry.setText(defaultFunction);
		
		save = new Button("Save");
		
		
		grid.add(name, 0, 0);
		grid.add(nameEntry, 1, 0);
		grid.add(function, 0, 1);
		grid.add(functionEntry, 1, 1);
		grid.add(save, 1, 2);
		grid.setHgap(5);
		grid.setVgap(5);
		
		Scene scene = new Scene(grid);
		window.setScene(scene);
		window.setTitle("Function Editor");
		window.show();
		
	}
	
	private void buttonAction(){
		if(nameEntry.getText().length() > 0){
			changedFunction.setName(nameEntry.getText());
		}
		if(functionEntry.getText().length() > 0){
			changedFunction.setCommands(functionEntry.getText());
		}
		window.close();
	}

}
