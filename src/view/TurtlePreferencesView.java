package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class TurtlePreferencesView implements View
{
	private TurtleView turtle;
	private VBox display;
	private List<Observable> updaters;

	public TurtlePreferencesView(TurtleView turtle)
	{
		this.turtle = turtle;
		updaters = new ArrayList<Observable>();
		display = new VBox();
		initializeButtons();
	}

	private void initializeButtons()
	{
		display.getChildren().add(new Label("Turtle Color:"));
		TurtleColorButton turtleColorButton = new TurtleColorButton();
		updaters.add(turtleColorButton);
		display.getChildren().add(turtleColorButton.display());
		display.getChildren().add(new Label("Turtle Image:"));
		TurtleImageButton turtleImageButton = new TurtleImageButton();
		updaters.add(turtleImageButton);
		display.getChildren().add(turtleImageButton.display());
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

	public void addObserver(Observer obs)
	{
		for (Observable updater : updaters) {
			updater.addObserver(obs);
		}
	}

	public class TurtleColorButton extends ColorButton
	{
		public TurtleColorButton()
		{
			super(null);
		}
	}

	public class TurtleImageButton extends ImageUpdater
	{
		public TurtleImageButton()
		{
			super("Image");
		}
	}

	// public class TurtleMoveUpdater extends MoveUpdater
	// {
	// public TurtleMoveUpdater()
	// {
	// super()
	// }
	// }

}
