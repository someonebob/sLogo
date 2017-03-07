package tool;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
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
		buttons.add(new TurtleImageButton());
		buttons.add(new PenColorButton());
		buttons.add(new LanguageButton());
		buttons.add(new DefaultButton());
	}

	@Override
	protected List<AbstractButton> getButtons()
	{
		return buttons;

	}

	private FileChooser setupFileChooser()
	{
		final String EXTENSION = "*.png";

		FileChooser chooser = new FileChooser();
		chooser.setTitle("New Image");
		File defaultDirectory = new File(System.getProperty("user.dir") + "/images");
		chooser.setInitialDirectory(defaultDirectory);
		chooser.getExtensionFilters().setAll(new ExtensionFilter("IMAGE", EXTENSION));

		return chooser;
	}

	public class BackgroundColorButton extends AbstractColorButton
	{
		public BackgroundColorButton()
		{
			super(new MenuItem("Background Color"));
		}
	}

	public class TurtleImageButton extends AbstractButton
	{
		public TurtleImageButton()
		{
			super(new MenuItem("Turtle Image"));
			this.getItem().setOnAction(e -> {
				Stage newWindow = new Stage();
				File selectedFile = setupFileChooser().showOpenDialog(newWindow);
				if (selectedFile != null) {
					Image newImage = new Image(selectedFile.toURI().toString());
					this.setChanged();
					this.notifyObservers(newImage);
				}
			});
		}
	}

	public class PenColorButton extends AbstractColorButton
	{
		public PenColorButton()
		{
			super(new MenuItem("Pen Color"));
		}
	}

	public class LanguageButton extends AbstractLanguageButton
	{
		public LanguageButton()
		{
			super(new MenuItem("Language"));

		}
	}
	
	public class DefaultButton extends AbstractButton
	{
		public DefaultButton()
		{
			super(new MenuItem("Set Defaults"));
			this.getItem().setOnAction(e -> new DefaultChooser());
		}
	}

}