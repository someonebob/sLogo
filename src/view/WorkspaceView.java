package view;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import tool.VariableEditor;
import user_structures.VariableData;

/**
 * Workspace that stores all the variables
 * 
 * @author Jesse
 *
 */
public class WorkspaceView implements View
{
	private BorderPane pane;
	private TableView<VariableData> table;
	private TableColumn<VariableData, String> variables;
	private TableColumn<VariableData, Double> values;
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

	public void setItems(ObservableList<VariableData> data)
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
		pane.setTop(edit);
		pane.setCenter(table);

	}

	private void buttonAction()
	{
		if (table.getSelectionModel().getSelectedItem() != null) {
			VariableData variable = table.getSelectionModel().getSelectedItem();
			VariableEditor editor = new VariableEditor(variable);
			table.getItems().set(table.getSelectionModel().getSelectedIndex(), editor.getChangedVariable());
		}

	}
}
