package tool;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.ColorPicker;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.paint.Color;

/**
 * @author jimmy
 * @author Jesse
 *
 */
public class SettingsTool extends Tool
{
	public static final String name = "Settings";

	private List<AbstractButton> buttons;

	public SettingsTool()
	{
		super(name);
	}

	@Override
	public void makeMenuItems()
	{
		buttons = new ArrayList<>();
		buttons.add(new BackgroundColorButton());
		buttons.add(new ActorColorButton());
		buttons.add(new PenColorButton());
		buttons.add(new LanguageButton());
	}

	@Override
	public List<AbstractButton> getButtons()
	{
		return buttons;

	}

	public class BackgroundColorButton extends AbstractButton
	{
		private Color color;

		public BackgroundColorButton()
		{
			super(new Menu("Background Color"));
			MenuItem blue = new MenuItem("blue");
			MenuItem red = new MenuItem("red");
			MenuItem yellow = new MenuItem("yellow");
			((Menu) this.getItem()).getItems().addAll(blue, red, yellow);
			this.getItem().setOnAction(e -> {
				final ColorPicker colorPicker = new ColorPicker();
				colorPicker.setValue(Color.CORAL);
			});
			// blue.setOnAction(e -> {
			// System.out.println("hi");
			// this.color = Color.BLUE;
			// this.setChanged();
			// this.notifyObservers(color);
			// });
			// red.setOnAction(e -> {
			// this.color = Color.RED;
			// this.setChanged();
			// this.notifyObservers(color);
			// });
			// yellow.setOnAction(e -> {
			// this.color = Color.YELLOW;
			// this.setChanged();
			// this.notifyObservers(color);
			// });
		}
	}

	public class ActorColorButton extends AbstractButton
	{
		private Color color;

		public ActorColorButton()
		{
			super(new MenuItem("Actor Color"));
		}
	}

	public class PenColorButton extends AbstractButton
	{
		private Color color;

		public PenColorButton()
		{
			super(new MenuItem("Pen Color"));
		}
	}

	public class LanguageButton extends AbstractButton
	{
		public LanguageButton()
		{
			super(new MenuItem("Language"));
		}
	}

}
