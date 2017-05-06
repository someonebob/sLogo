package view;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

public class TurtlePreferencesView implements View
{
	private TurtleView turtle;
	private VBox display;

	public TurtlePreferencesView(TurtleView turtle)
	{
		this.turtle = turtle;
		display = new VBox();
		display.setAlignment(Pos.CENTER);
		turtle.getProperties().forEach(property ->
			display.getChildren().add(property.displayDynamicUpdater()));
	}
	
	@Override
	public Node display()
	{
		return display;
	}
}
