package view;

import java.io.File;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import util.FileChooserUtil;

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
			File selectedFile = FileChooserUtil
					.setupFileChooser("IMAGE", "IMAGE", new File(System.getProperty("user.dir") + "/images"), "*.png")
					.showOpenDialog(newWindow);
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

}
