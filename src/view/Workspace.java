package view;

import java.util.Observable;

import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * 
 * @author Jesse
 *
 */
public class Workspace implements View{
	private ScrollPane scroll;
	private VBox variables;
	
	public Workspace(){
		initiateItems();
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
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
