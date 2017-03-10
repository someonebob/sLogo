package view;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

public class PenPreferencesView implements View
{
	private PenView pen;
	private VBox display;

	public PenPreferencesView(TurtleView turtle)
	{
		this.pen = turtle.getPen();
		display = new VBox();
		display.setAlignment(Pos.CENTER);
		pen.getProperties().forEach(property -> display.getChildren().add(property.displayDynamicUpdater()));
	}

	@Override
	public Node display()
	{
		return display;
	}

}
