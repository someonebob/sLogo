package view;

import java.util.Observable;

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
