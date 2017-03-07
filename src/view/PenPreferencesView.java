package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
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
		PenThicknessUpdater penThicknessUpdater = new PenThicknessUpdater();
		updaters.add(penThicknessUpdater);
		display.getChildren().add(penThicknessUpdater.display());
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

	public class PenThicknessUpdater extends Observable
	{
		private TextField input;

		public PenThicknessUpdater()
		{
			input = new TextField();
			input.setText(String.valueOf(pen.getThickness()));
			input.setOnKeyPressed(e -> {
				if (e.getCode() == KeyCode.ENTER) {
					this.setChanged();
					this.notifyObservers(input.getText());
				}
			});
		}

		public Node display()
		{
			return input;
		}
	}

}
