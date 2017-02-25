package view;

import java.util.Observable;

import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

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
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		if (arg instanceof String){
			Text variable = new Text((String) arg);
			variables.getChildren().add(variable);
		}
		
	}
	
	@Override
	public Node display() {
		return scroll;
	}
	
	private void initiateItems(){
		scroll = new ScrollPane();
		variables = new VBox(10);
		variables.setPrefWidth(200);
		scroll.setContent(variables);
	}




}
