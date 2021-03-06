package property;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * 
 * @author jimmy
 * @author Matthew Barbano
 */
public abstract class AbstractColorProperty extends Property<Color>
{
	private static final List<Color> INDEXED_COLORS = new ArrayList<>();
	private HBox colorPalette;

	// TODO Change to read in XML File
	static {
		INDEXED_COLORS.add(Color.rgb(255, 0, 0));
		INDEXED_COLORS.add(Color.rgb(0, 255, 0));
		INDEXED_COLORS.add(Color.rgb(0, 0, 255));
	}

	public AbstractColorProperty(String name)
	{
		super(name);
		colorPalette = new HBox();
		colorPalette.setAlignment(Pos.CENTER);
		updateColorPalette(colorPalette);
	}

	public List<Color> getIndexedColors()
	{
		updateColorPalette(colorPalette);
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

	public void updateColorPalette()
	{
		this.updateColorPalette(colorPalette);
	}

	private void updateColorPalette(HBox colorPalette)
	{
		colorPalette.getChildren().clear();
		for (int i = 0; i < INDEXED_COLORS.size(); i++) {
			Rectangle added = new Rectangle(30, 30, INDEXED_COLORS.get(i));
			Text text = new Text(String.valueOf(i));
			StackPane stackedColor = new StackPane();
			stackedColor.getChildren().addAll(added, text);
			colorPalette.getChildren().add(stackedColor);
		}
	}

	public Node getColorPalette()
	{
		updateColorPalette(colorPalette);
		return colorPalette;
	}

	protected abstract void updateColor(Color color);
}
