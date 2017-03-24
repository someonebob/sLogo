package property;

import javafx.animation.TranslateTransition;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Point2D;
import javafx.util.Duration;
import view.ActorView;

/**
 * 
 * @author jimmy
 *
 */
public class ActorPositionProperty extends Property<Point2D>
{
	private ActorView actor;
	private StringProperty position;

	public ActorPositionProperty(String name, ActorView actor)
	{
		super(name, Point2D.class);
		this.actor = actor;
		position = new SimpleStringProperty();
		super.setValue(new Point2D(0, 0));
		position.set(this.getValue().toString());
	}

	@Override
	public void setValue(Point2D location)
	{
		actorMove(location);
		super.setValue(location);
		position.set(this.getValue().toString());
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
			position.set(this.getValue().toString());
		}
	}

	// @Override
	// public List<Node> makeDynamicUpdaters()
	// {
	// // //Label label = new Label(String.format("Move %s", this.getName()));
	// VBox xBox = new VBox();
	// VBox yBox = new VBox();
	// HBox xyBox = new HBox();
	// xBox.getChildren().add(new Label("x"));
	// yBox.getChildren().add(new Label("y"));
	// TextField x = new TextField();
	// TextField y = new TextField();
	// xBox.getChildren().add(x);
	// yBox.getChildren().add(y);
	// xyBox.setPrefWidth(100);
	// xyBox.getChildren().addAll(xBox, yBox);
	// // TODO: REPLACE THESE WITH RESOURCE FILE
	// // ComboBox<String> directionPicker = new
	// // ComboBox<>(FXCollections.observableArrayList("fd", "bk", "lt",
	// // "rt"));
	// // TextField distance = new TextField();
	// // distance.setPromptText("Amount to move");
	// // Button moveButton = new Button("Move");
	// Button moveButton = new Button("Move");
	//
	// moveButton.setOnAction(e -> {
	// actor.move(new Point2D(Integer.valueOf(x.getText()),
	// Integer.valueOf(y.getText())));
	// actor.step();
	// });
	//
	// return Arrays.asList(xyBox, moveButton);
	// // return Arrays.asList(label, directionPicker, distance, moveButton);
	// }

	public StringProperty getLocationAsString()
	{
		return position;
	}

	private void actorMove(Point2D newLocation)
	{
		TranslateTransition move = new TranslateTransition(Duration.millis(1000 / actor.getSpeed()));

		move.setFromX(this.getValue().getX());
		move.setToX(newLocation.getX());
		move.setFromY(this.getValue().getY());
		move.setToY(newLocation.getY());
		move.setCycleCount(1);

		actor.addTransition(move);
	}

}
