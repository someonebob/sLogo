package view;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import tool.FunctionEditor;
import tool.VariableEditor;
import user_structures.FunctionData;
import user_structures.VariableData;

/**
 * Workspace that stores all the variables
 * 
 * @author Jesse
 *
 */
public class SavedStructuresView implements View {
	private BorderPane pane;
	private TableView<VariableData> varTable;
	private TableColumn<VariableData, String> variables;
	private TableColumn<VariableData, Double> values;
	private TableView<FunctionData> funcTable;
	private TableColumn<FunctionData, String> funcNames;
	private TableColumn<FunctionData, Double> functions;
	private HBox buttonBox;
	private Button varEdit;
	private Button funcEdit;

	public SavedStructuresView() {
		initiateSetup();
		initiateVariableItems();
		initiateFunctionItems();

		varEdit.setOnAction(e -> varButtonAction());
		funcEdit.setOnAction(e -> funcButtonAction());

	}

	@Override
	public Node display() {
		return pane;
	}

	public void setVariables(ObservableList<VariableData> data) {
		varTable.setItems(data);
	}

	public void setFunctions(ObservableList<FunctionData> data) {
		funcTable.setItems(data);
	}

	private void initiateSetup() {
		pane = new BorderPane();
		buttonBox = new HBox();
		pane.setTop(buttonBox);
	}

	private void initiateVariableItems() {
		varTable = new TableView<>();
		varTable.setPrefWidth(300);
		varTable.setPrefHeight(300);

		variables = new TableColumn<>("Variable Name");
		variables.setCellValueFactory(new PropertyValueFactory("name"));
		variables.setPrefWidth(150);
		values = new TableColumn<>("Value");
		values.setCellValueFactory(new PropertyValueFactory("value"));
		values.setPrefWidth(150);

		varTable.getColumns().setAll(variables, values);

		varEdit = new Button("Edit Variable");
		buttonBox.getChildren().add(varEdit);

		pane.setCenter(varTable);

	}

	private void initiateFunctionItems() {
		funcTable = new TableView<>();
		funcTable.setPrefWidth(300);

		funcNames = new TableColumn<>("Command Name");
		funcNames.setCellValueFactory(new PropertyValueFactory("name"));
		funcNames.setPrefWidth(150);
		functions = new TableColumn<>("Commands");
		functions.setCellValueFactory(new PropertyValueFactory("commands"));
		functions.setPrefWidth(150);

		funcTable.getColumns().setAll(funcNames, functions);

		funcEdit = new Button("Edit Command");
		buttonBox.getChildren().add(funcEdit);

		pane.setBottom(funcTable);

	}

	private void varButtonAction() {
		if (varTable.getSelectionModel().getSelectedItem() != null) {
			VariableData variable = varTable.getSelectionModel().getSelectedItem();
			VariableEditor editor = new VariableEditor(variable);
			varTable.getItems().set(varTable.getSelectionModel().getSelectedIndex(), editor.getChangedVariable());
		}

	}

	private void funcButtonAction() {
		if (funcTable.getSelectionModel().getSelectedItem() != null) {
			FunctionData function = funcTable.getSelectionModel().getSelectedItem();
			FunctionEditor editor = new FunctionEditor(function);
			funcTable.getItems().set(funcTable.getSelectionModel().getSelectedIndex(), editor.getChangedFunction());
		}
	}
}
