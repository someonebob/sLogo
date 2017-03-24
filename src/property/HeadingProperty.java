package property;

import java.util.Arrays;
import java.util.List;

import javafx.animation.RotateTransition;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
		super(name, Double.class);
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
	public List<Node> makeDynamicUpdaters()
	{
		Label label = new Label(String.format("Set %s", this.getName()));
		TextField input = new TextField();
		input.setPromptText(this.getValue().toString());

		input.setText(this.getValue().toString());
		input.setOnAction(e -> {
			this.setValue(input.getText());
			actor.step();
		});

		return Arrays.asList(label, input);
	}

	private void setActorHeading(double heading)
	{
		actor.addTransition(makeRotateTransition(this.getValue(), heading));
	}

	private RotateTransition makeRotateTransition(double startAngle, double endAngle)
	{
		RotateTransition rotate = new RotateTransition(Duration.millis(1000 / actor.getSpeed()));
		rotate.setFromAngle(startAngle);
		rotate.setToAngle(endAngle);
		rotate.setCycleCount(1);
		return rotate;
	}

}
