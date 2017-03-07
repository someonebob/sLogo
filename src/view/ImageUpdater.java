package view;

import java.io.File;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public abstract class ImageUpdater extends AbstractUpdater
{

	private Button input;

	public ImageUpdater(String defaultText)
	{
		input = initializeButton(defaultText);
	}

	private Button initializeButton(String defaultText)
	{
		Button input = new Button();
		input.setText(defaultText);
		Stage newWindow = new Stage();
		input.setOnAction(e -> {
			File selectedFile = setupFileChooser().showOpenDialog(newWindow);
			if (selectedFile != null) {
				Image newImage = new Image(selectedFile.toURI().toString());
				this.setChanged();
				this.notifyObservers(newImage);
			}
		});
		return input;
	}

	@Override
	public Node display()
	{
		return input;
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

}
