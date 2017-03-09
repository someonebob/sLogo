package property;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public abstract class AbstractColorProperty extends Property<Color>
{
	public AbstractColorProperty(String name)
	{
		super(name);
	}

	@Override
	public void setValue(Color color)
	{
		super.setValue(color);
		updateColor(color);
	}

	@Override
	public void setValue(String stringValue)
	{
		this.setValue(Color.web(stringValue));
	}

	@Override
	public Node makeDynamicUpdater()
	{
		VBox vbox = new VBox();
		Label label = new Label(String.format("Update %s", this.getName()));
		ColorPicker colorPicker = initializeColorPicker();

		vbox.getChildren().add(label);
		vbox.getChildren().add(colorPicker);
		vbox.setAlignment(Pos.CENTER);
		return vbox;
	}

	private ColorPicker initializeColorPicker()
	{
		ColorPicker colorPicker = new ColorPicker();
		colorPicker.setValue(this.getValue());
		colorPicker.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent t)
			{
				setValue(colorPicker.getValue());
			}
		});
		return colorPicker;
	}

	protected abstract void updateColor(Color color);
}
