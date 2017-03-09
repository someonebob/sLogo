package property;

import java.util.Arrays;
import java.util.List;

import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import util.MathUtil;
import util.PointPolar;
import view.ActorView;

/**
 * 
 * @author jimmy
 *
 */
public class ActorPositionProperty extends Property<Point2D>
{
	private ActorView actor;

	public ActorPositionProperty(String name, ActorView actor)
	{
		super(name);
		this.actor = actor;
		super.setValue(new Point2D(0, 0));
	}

	@Override
	public void setValue(Point2D location)
	{
		actorMove(location);
		super.setValue(location);
	}

	@Override
	public void setValue(String stringValue)
	{
		stringValue.replace("(", "");
		stringValue.replace(")", "");
		String[] coordinates = stringValue.split(",");
		if (coordinates.length == 2) {
			double x = Double.parseDouble(coordinates[0]);
			double y = Double.parseDouble(coordinates[1]);
			this.setValue(new Point2D(x, y));
		}
	}

	@Override
	public List<Node> makeDynamicUpdaters()
	{
		Label label = new Label(String.format("Move %s", this.getName()));
		// TODO: REPLACE THESE WITH RESOURCE FILE
		ComboBox<String> directionPicker = new ComboBox<>(FXCollections.observableArrayList("fd", "bk", "lt", "rt"));
		TextField distance = new TextField();
		distance.setPromptText("Amount to move");
		Button moveButton = new Button("Move");

		moveButton.setOnAction(e -> {
			if (directionPicker.getValue().equals("fd")) {
				move(Double.parseDouble(distance.getText()));
			} else if (directionPicker.getValue().equals("bk")) {
				move(-1 * Double.parseDouble(distance.getText()));
			} else if (directionPicker.getValue().equals("lt")) {
				actor.setHeading(actor.getHeading() - Double.parseDouble(distance.getText()));
			} else if (directionPicker.getValue().equals("rt")) {
				actor.setHeading(actor.getHeading() + Double.parseDouble(distance.getText()));
			}
			actor.step();
		});
		
		return Arrays.asList(label, directionPicker, distance, moveButton);
	}

	private void move(double distance)
	{
		Point2D currentLocation = this.getValue();
		double currentHeading = actor.getHeading();
		Point2D deltaVector = MathUtil.polarToRectangular(new PointPolar(distance, currentHeading));
		Point2D newLocation = currentLocation.add(deltaVector);
		actorMove(newLocation);
		this.setValue(newLocation);
	}

	private void actorMove(Point2D newLocation)
	{
		TranslateTransition move = new TranslateTransition(Duration.millis(1000/actor.getSpeed()));
		move.setFromX(this.getValue().getX());
		move.setToX(newLocation.getX());
		move.setFromY(this.getValue().getY());
		move.setToY(newLocation.getY());
		move.setCycleCount(1);
		actor.addTransition(move);
	}
}
