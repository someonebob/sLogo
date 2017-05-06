package view;

import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

public class AllTurtlesPreferencesView implements View
{
	private List<TurtleView> turtles;
	private VBox display;

	public AllTurtlesPreferencesView(List<TurtleView> turtles){
		this.turtles = turtles;
		display = new VBox();
		display.setAlignment(Pos.CENTER);
		turtles.forEach(turtle -> 
				display.getChildren().add(turtle.getImageProperty().displayDynamicUpdater()));
		
	}
	
	@Override
	public Node display(){
		return display;
	}
}