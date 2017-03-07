package view;

import java.util.Observable;

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
		initializeButtons();
	}

	private void initializeButtons()
	{
		display.getChildren().add(new PenColorButton().display());
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

	public class PenColorButton extends ColorButton
	{
		public PenColorButton()
		{
			super(pen.getColor());
		}
	}

}
