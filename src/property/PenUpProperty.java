package property;

import java.util.Arrays;
import java.util.List;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.paint.Color;

/**
 * 
 * @author jimmy
 *
 */
public class PenUpProperty extends Property<Boolean>
{
	PenColorProperty color;

	public PenUpProperty(String name, PenColorProperty color)
	{
		super(name, Boolean.class);
		this.color = color;
	}

	@Override
	public void setValue(Boolean up)
	{
		super.setValue(up);
		if (up) {
			color.setValue(Color.TRANSPARENT);
		} else {
			color.setValue(color.getValue());
		}
	}

	@Override
	public void setValue(String stringValue)
	{
		this.setValue(Boolean.getBoolean(stringValue));
	}

	@Override
	public List<Node> makeDynamicUpdaters()
	{
		Label label = new Label(String.format("Set %s", this.getName()));
		// TODO: REPLACE THESE WITH RESOURCE FILE
		ToggleButton upDownButton = new ToggleButton(this.getValue() ? "up" : "down");

		upDownButton.setOnAction(e -> {
			if (upDownButton.getText().equals("down")) {
				this.setValue(true);
				upDownButton.setText("up");
			} else if (upDownButton.getText().equals("up")) {
				this.setValue(false);
				upDownButton.setText("down");
			}
		});

		return Arrays.asList(label, upDownButton);
	}
}
