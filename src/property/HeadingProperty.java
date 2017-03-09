package property;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
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
		super.setValue(actor.getImageProperty().getValue().getRotate());
	}

	@Override
	public void setValue(Double heading)
	{
		super.setValue((double) Math.floorMod(heading.intValue(), 360));
		actor.setHeading(heading);
		actor.step();
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

}
