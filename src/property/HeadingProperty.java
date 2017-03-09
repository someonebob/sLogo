package property;

import javafx.animation.RotateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import view.ActorView;

/**
 * 
 * @author jimmy
 *
 */
public class HeadingProperty extends Property<Double>
{
	private ActorView actor;

	public HeadingProperty(String name, ActorView actor)
	{
		super(name);
		this.actor = actor;
		super.setValue(0.0);
	}

	@Override
	public void setValue(Double heading)
	{
		setActorHeading(heading);
		super.setValue((double) Math.floorMod(heading.intValue(), 360));
		// actor.step();
	}

	@Override
	public void setValue(String stringValue)
	{
		this.setValue(Double.valueOf(stringValue));
	}

	@Override
	public Node makeDynamicUpdater()
	{
		VBox vbox = new VBox();
		Label label = new Label(String.format("Set %s", this.getName()));
		TextField input = new TextField();
		input.setText(this.getValue().toString());
		input.setOnAction(e -> {
			this.setValue(input.getText());
		});
		// update when box is deselected
		input.focusedProperty().addListener(new ChangeListener<Boolean>()
		{
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue)
			{
				if (!newValue && oldValue) {
					setValue(input.getText());
				}
			}
		});
		vbox.getChildren().addAll(label, input);
		vbox.setAlignment(Pos.CENTER);
		return vbox;
	}

	private void setActorHeading(double heading)
	{
		actor.addTransition(makeRotateTransition(this.getValue(), heading));
	}

	private RotateTransition makeRotateTransition(double startAngle, double endAngle)
	{
		RotateTransition rotate = new RotateTransition(Duration.millis(200));
		rotate.setFromAngle(startAngle);
		rotate.setToAngle(endAngle);
		rotate.setCycleCount(1);
		return rotate;
	}

}
