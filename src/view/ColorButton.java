package view;

import java.util.Observable;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;

public abstract class ColorButton extends Observable
{
	private Color color;
	final ColorPicker colorPicker;

	public ColorButton(Color defaultColor)
	{
		colorPicker = new ColorPicker();
		color = defaultColor;
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

	}

	public Node display()
	{
		return colorPicker;
	}

}
