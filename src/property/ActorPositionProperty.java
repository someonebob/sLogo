package property;

import javafx.collections.FXCollections;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
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
	ActorView actor;

	public ActorPositionProperty(String name, ActorView actor)
	{
		super(name);
		this.actor = actor;
		super.setValue(actor.getActor().getLocation());
	}

	@Override
	public void setValue(Point2D location)
	{
		super.setValue(location);
		actor.move(location);
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
	public Node makeDynamicUpdater()
	{
		VBox vbox = new VBox();
		Label label = new Label(String.format("Move %s", this.getName()));
		// TODO: REPLACE THESE WITH RESOURCE FILE
		ComboBox<String> directionPicker = new ComboBox<>(FXCollections.observableArrayList("fd", "bk", "lt", "rt"));
		TextField distance = new TextField("Amount to move");
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

		vbox.getChildren().add(label);
		vbox.getChildren().add(directionPicker);
		vbox.getChildren().add(distance);
		vbox.getChildren().add(moveButton);

		return vbox;
	}

	protected void move(double distance)
	{
		Point2D currentLocation = this.getValue();
		double currentHeading = actor.getHeading();
		Point2D deltaVector = MathUtil.polarToRectangular(new PointPolar(distance, currentHeading));
		Point2D newLocation = currentLocation.add(deltaVector);
		actor.move(newLocation);
		this.setValue(newLocation);
	}

	private void updatePosition(Point2D newPosition)
	{
		actor.move(newPosition);
	}
}
