package tool;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.TransformerException;

import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import xml.XMLEditor;
import xml.XMLException;
import javafx.stage.Stage;

/**
 * @author jimmy
 * @author Jesse
 *
 */
public class SettingsMenuTool extends MenuTool
{
	public static final String name = "Settings";

	private List<AbstractMenuItem> buttons;

	public SettingsMenuTool(Stage window)
	{
		super(name, window);
	}

	@Override
	public void makeItems()
	{
		buttons = new ArrayList<>();
		addButtons(buttons, new BackgroundColorButton(), new TurtleImageButton(), new PenColorButton(), new LanguageButton(), new DefaultButton());
	}

	@Override
	protected List<AbstractMenuItem> getButtons()
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

	public class TurtleImageButton extends AbstractMenuItem
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
	
	public class DefaultButton extends AbstractMenuItem
	{
		public DefaultButton()
		{
			super(new MenuItem("Save Defaults"));
			this.getItem().setOnAction(e -> {
				this.setChanged();
				this.notifyObservers();
			});
		}
	}

}