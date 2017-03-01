package view;

import java.util.Observable;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

/**
 * Workspace that stores all the variables
 * @author Jesse
 *
 */
public class WorkspaceView implements PageView{
	private ScrollPane scroll;
	private VBox variables;
	
	public WorkspaceView(){
		initiateItems();
	}
	
	@Override
	public Node display() {
		return scroll;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void updateData(String arg) {
		Label variable = new Label(arg);
		variable.setOnMouseClicked(e -> {
			//TODO: let variables be editable
		});
		variables.getChildren().add(variable);

		
	}
	
	public VBox getVariables(){
		return variables;
	}

	private void initiateItems(){
		scroll = new ScrollPane();
		variables = new VBox(10);
		variables.setPrefWidth(200);
		scroll.setContent(variables);
	}






}
