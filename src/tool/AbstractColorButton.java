package tool;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class AbstractColorButton extends AbstractMenuItem
{
	private Color color;

	public AbstractColorButton(MenuItem menu)
	{
		super(menu);
		this.getItem().setOnAction(e -> {
			final ColorPicker colorPicker = new ColorPicker();
			colorPicker.setValue(color);
			Stage newWindow = new Stage();
			HBox colorBox = new HBox(20);
			Scene scene = new Scene(colorBox);
			colorBox.getChildren().add(colorPicker);
			newWindow.setScene(scene);
			newWindow.setTitle("Color");
			newWindow.show();
			colorPicker.setOnAction(new EventHandler<ActionEvent>()
			{
				@Override
				public void handle(ActionEvent t)
				{
					color = colorPicker.getValue();
					newWindow.close();
					setChanged();
					notifyObservers(color);
				}
			});

		});
	}

}
