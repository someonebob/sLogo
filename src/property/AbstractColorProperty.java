package property;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

/**
 * 
 * @author jimmy
 *
 */
public abstract class AbstractColorProperty extends Property<Color>
{
	private static final List<Color> INDEXED_COLORS = new ArrayList<>();

	// TODO Change to read in XML File
	static {
		INDEXED_COLORS.add(Color.rgb(255, 0, 0));
		INDEXED_COLORS.add(Color.rgb(0, 255, 0));
		INDEXED_COLORS.add(Color.rgb(0, 0, 255));
	}

	public AbstractColorProperty(String name)
	{
		super(name);
	}

	public List<Color> getIndexedColors()
	{
		return INDEXED_COLORS;
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
	public List<Node> makeDynamicUpdaters()
	{
		Label label = new Label(String.format("Update %s", this.getName()));
		ColorPicker colorPicker = initializeColorPicker();

		return Arrays.asList(label, colorPicker);
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
