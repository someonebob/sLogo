package view;

import java.util.Observable;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import user_structures.Variable;

/**
 * Workspace that stores all the variables
 * 
 * @author Jesse
 *
 */
public class WorkspaceView implements PageView
{
	private BorderPane pane;
	private TableView<Variable> table;
	private TableColumn<Variable, String> variables;
	private TableColumn<Variable, Double> values;
	private Button edit;

	public WorkspaceView()
	{
		initiateItems();

		edit.setOnAction(e -> buttonAction());

	}

	@Override
	public Node display()
	{
		return pane;
	}

	@Override
	public void update(Observable o, Object arg)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void updateData(String arg)
	{

	}

	public void setItems(ObservableList<Variable> data)
	{
		table.setItems(data);
	}

	private void initiateItems()
	{
		pane = new BorderPane();
		table = new TableView<>();
		table.setPrefWidth(200);

		variables = new TableColumn<>("Variable Name");
		variables.setCellValueFactory(new PropertyValueFactory("name"));
		variables.setPrefWidth(120);
		values = new TableColumn<>("Value");
		values.setCellValueFactory(new PropertyValueFactory("value"));

		table.getColumns().setAll(variables, values);

		edit = new Button("Edit");
		// magic number to make it line up with tabs
		edit.setPrefHeight(38);
		pane.setTop(edit);
		pane.setCenter(table);

	}

	private void buttonAction()
	{
		if (table.getSelectionModel().getSelectedItem() != null) {
			Variable variable = table.getSelectionModel().getSelectedItem();
			VariableEditor editor = new VariableEditor(variable);
			table.getItems().set(table.getSelectionModel().getSelectedIndex(), editor.getChangedVariable());
		}

	}
}
