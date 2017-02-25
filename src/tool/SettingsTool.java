package tool;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

/**
 * @author jimmy
 * @author Jesse
 *
 */
public class SettingsTool extends Tool
{
	public static final String name = "Settings";

	private List<AbstractButton> buttons;

	public SettingsTool(Stage window)
	{
		super(name, window);
	}

	@Override
	public void makeMenuItems()
	{
		buttons = new ArrayList<>();
		buttons.add(new BackgroundColorButton());
		buttons.add(new ActorImageButton());
		buttons.add(new PenColorButton());
		buttons.add(new LanguageButton());
	}

	@Override
	public List<AbstractButton> getButtons()
	{
		return buttons;

	}

	public class BackgroundColorButton extends AbstractColorButton
	{
		public BackgroundColorButton()
		{
			super(new MenuItem("Background Color"));
		}
	}

	public class ActorImageButton extends AbstractButton
	{
		public ActorImageButton()
		{
			super(new MenuItem("Actor Image"));
		}
	}

	public class PenColorButton extends AbstractColorButton
	{
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
