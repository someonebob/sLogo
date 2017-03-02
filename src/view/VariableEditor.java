package view;


import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import user_structures.VariableData;

/**
 * Window used to edit variables from the workspace
 * @author Jesse
 *
 */
public class VariableEditor {
	private Stage window;
	private GridPane grid;
	private TextField variableEntry;
	private TextField valueEntry;
	private Button save;
	private VariableData changedVariable;
	
	public VariableEditor(VariableData variable){
		initiateItems(variable.getName(), variable.getValue());
		save.setOnAction(e -> buttonAction());
	}
	
	public VariableData getChangedVariable(){
		return changedVariable;
	}
	
	private void initiateItems(String defaultName, double defaultValue){
		window = new Stage();
		grid = new GridPane();

		changedVariable = new VariableData(defaultName, defaultValue);
		Label variable = new Label("Variable");
		Label value = new Label("Value");
		
		variableEntry = new TextField();
		variableEntry.setPromptText(defaultName);
		variableEntry.setFocusTraversable(false);
		
		valueEntry = new TextField();
		valueEntry.setPromptText(Double.toString(defaultValue));
		valueEntry.setFocusTraversable(false);
				
		save = new Button("Save");
		
		
		grid.add(variable, 0, 0);
		grid.add(variableEntry, 1, 0);
		grid.add(value, 0, 1);
		grid.add(valueEntry, 1, 1);
		grid.add(save, 1, 2);
		
		Scene scene = new Scene(grid);
		window.setScene(scene);
		window.setTitle("Variable Editor");
		window.show();
	}
	
	private void buttonAction(){
		if(variableEntry.getText().length() > 0){
			changedVariable.setName(variableEntry.getText());
		}
		if(valueEntry.getText().length() > 0){
			//Check if works for negative numbers
			if(valueEntry.getText().chars().allMatch( Character::isDigit )){
				changedVariable.setValue(Double.parseDouble(valueEntry.getText()));
			}else{
				//TODO make it pop up an alert that says value input must be a number
			}
		}
		window.close();
	}

}