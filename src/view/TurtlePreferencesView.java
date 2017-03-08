package view;

import java.util.Observable;

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
		turtle.getProperties().forEach(property -> display.getChildren().add(property.makeDynamicUpdater()));
	}

	@Override
	public void update(Observable o, Object arg)
	{

	}

	@Override
	public Node display()
	{
		return display;
	}
}
