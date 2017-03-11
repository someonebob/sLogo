package tool;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import util.FileChooserUtil;

/**
 * This tool handles everything related to files
 * 
 * @author Jesse
 *
 */
public class FileMenuTool extends MenuTool
{

	public static final String name = "File";

	private List<AbstractMenuItem> buttons;

	public FileMenuTool(Stage window)
	{
		super(name, window);
	}

	@Override
	public void makeItems()
	{
		buttons = new ArrayList<>();
		buttons.add(new NewButton());
		buttons.add(new OpenButton());
		buttons.add(new SaveButton());
		buttons.add(new SaveImageButton());
	}

	@Override
	protected List<AbstractMenuItem> getButtons()
	{
		return buttons;
	}

	/**
	 * Button that creates a new file
	 * 
	 * @author Jesse
	 *
	 */
	public class NewButton extends AbstractMenuItem
	{

		public NewButton()
		{
			super(new MenuItem("New"));
			// TODO action
			this.getItem().setOnAction(e -> {
				this.setChanged();
				this.notifyObservers();
			});
		}

	}

	/**
	 * Button that opens an existing file
	 * 
	 * @author Jesse
	 *
	 */
	public class OpenButton extends AbstractMenuItem
	{

		public OpenButton()
		{
			super(new MenuItem("Open"));
			// TODO Auto-generated constructor stub
			this.getItem().setOnAction(e -> {
				File selectedFile = FileChooserUtil.setupFileChooser("LOGO", "LOGO Programs",
						new File(System.getProperty("user.dir") + "/data"), "*.logo").showSaveDialog(getStage());
				if (selectedFile != null) {
					this.setChanged();
					this.notifyObservers(selectedFile);
				}

			});
		}

	}

	/**
	 * Button that saves the file
	 * 
	 * @author Jesse
	 *
	 */
	public class SaveButton extends AbstractMenuItem
	{

		public SaveButton()
		{
			super(new MenuItem("Save"));
			this.getItem().setOnAction(e -> {
				File save = FileChooserUtil.setupFileChooser("LOGO", "LOGO Programs",
						new File(System.getProperty("user.dir") + "/data"), "*.logo").showSaveDialog(getStage());
				if (save != null) {
					this.setChanged();
					this.notifyObservers(save);
				}

			});
		}

	}

	/**
	 * Button that saves the view as an image
	 * 
	 * @author Jesse
	 *
	 */
	public class SaveImageButton extends AbstractMenuItem
	{

		public SaveImageButton()
		{
			super(new MenuItem("Save Image"));
			this.getItem().setOnAction(e -> {
				File save = FileChooserUtil
						.setupFileChooser("PNG", "Saved Images",
								new File(System.getProperty("user.dir") + "/saved_images"), "*.png")
						.showSaveDialog(getStage());
				if (save != null) {
					this.setChanged();
					this.notifyObservers(save);
				}

			});
		}

	}

}