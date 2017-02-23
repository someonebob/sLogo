package view;

import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Workspace implements PageView{
	private ScrollPane scroll;
	private VBox variables;
	
	public Workspace(){
		initiateItems();
	}

	@Override
	public void update(String instruction) {
		// TODO Auto-generated method stub
		Text variable = new Text(instruction);
		variables.getChildren().add(variable);
	}

	@Override
	public Node display() {
		// TODO Auto-generated method stub
		return scroll;
	}
	
	private void initiateItems(){
		scroll = new ScrollPane();
		variables = new VBox(10);
		variables.setPrefWidth(200);
		scroll.setContent(variables);
	}

}
