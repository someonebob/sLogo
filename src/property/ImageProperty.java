package property;

import java.io.File;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 * 
 * @author jimmy
 *
 */
public class ImageProperty extends Property<ImageView>
{

	public ImageProperty(String name)
	{
		super(name);
		super.setValue(new ImageView());
	}

	@Override
	public void setValue(ImageView image)
	{
		this.setValue(image.getImage());
	}

	public void setValue(Image image)
	{
		this.getValue().setImage(image);
	}

	@Override
	public void setValue(String stringValue)
	{
		this.setValue(new Image(stringValue));
	}

	@Override
	public Node makeDynamicUpdater()
	{
		Label label = new Label(String.format("Update %s", this.getName()));
		Button input = initializeButton(String.format("Update %s", this.getName()));
		VBox vbox = new VBox();

		vbox.getChildren().add(label);
		vbox.getChildren().add(input);

		return vbox;
	}

	private Button initializeButton(String defaultText)
	{
		Button input = new Button();
		input.setText(defaultText);
		Stage newWindow = new Stage();
		input.setOnAction(e -> {
			File selectedFile = setupFileChooser().showOpenDialog(newWindow);
			if (selectedFile != null) {
				this.getValue().setImage(new Image(selectedFile.toURI().toString()));
			}
		});
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
