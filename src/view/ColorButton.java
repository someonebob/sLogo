package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;

public abstract class ColorButton extends AbstractUpdater
{

	private ColorPicker colorPicker;
	private Color color;

	public ColorButton(Color defaultColor)
	{
		colorPicker = initializeColorPicker(defaultColor);
	}

	private ColorPicker initializeColorPicker(Color defaultColor)
	{
		ColorPicker colorPicker = new ColorPicker();
		colorPicker.setValue(defaultColor);
		colorPicker.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent t)
			{
				color = colorPicker.getValue();
				setChanged();
				notifyObservers(color);
			}
		});
		return colorPicker;
	}

	@Override
	public Node display()
	{
		return colorPicker;
	}

}
