package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class PenPreferencesView implements View
{
	private PenView pen;
	private VBox display;
	private List<Observable> updaters;

	public PenPreferencesView(TurtleView turtle)
	{
		this.pen = turtle.getPen();
		updaters = new ArrayList<Observable>();
		display = new VBox();
		initializeButtons();
	}

	private void initializeButtons()
	{
		display.getChildren().add(new Label("Pen Color:"));
		PenColorButton penColorButton = new PenColorButton();
		updaters.add(penColorButton);
		display.getChildren().add(penColorButton.display());
		display.getChildren().add(new Label("Pen Thickness:"));
		PenThicknessUpdater penThicknessUpdater = new PenThicknessUpdater();
		updaters.add(penThicknessUpdater);
		display.getChildren().add(penThicknessUpdater.display());
		display.getChildren().add(new Label("Pen Up/Down:"));
		PenUpDownUpdater penUpDownUpdater = new PenUpDownUpdater();
		updaters.add(penUpDownUpdater);
		display.getChildren().add(penUpDownUpdater.display());
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

	public class PenColorButton extends ColorButton
	{
		public PenColorButton()
		{
			super(pen.getColor());
		}
	}

	public class PenThicknessUpdater extends TextUpdater
	{
		public PenThicknessUpdater()
		{
			super(String.valueOf(pen.getThickness()));
		}
	}

	public class PenUpDownUpdater extends ComboUpdater<String>
	{

		public PenUpDownUpdater()
		{
			super(pen.isUp() ? "Up" : "Down", "Up", "Down");
		}

	}

}
