package property;

import java.io.File;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import view.ActorView;

/**
 * 
 * @author jimmy
 *
 */
public class ImageProperty extends Property<ImageView>
{
	private ImageView displayImage;

	public ImageProperty(String name)
	{
		super(name);
		displayImage = new ImageView();
		super.setValue(new ImageView());
		displayImage.setFitHeight(ActorView.ACTOR_HEIGHT);
		displayImage.setFitWidth(ActorView.ACTOR_WIDTH);
		displayImage.setPreserveRatio(true);
	}

	@Override
	public void setValue(ImageView image)
	{
		this.setValue(image.getImage());
	}

	public void setValue(Image image)
	{
		this.getValue().setImage(image);
		updateDisplay();
	}

	@Override
	public void setValue(String stringValue)
	{
		this.setValue(new Image(stringValue));
	}

	@Override
	public void updateDisplay()
	{
		displayImage.setImage(this.getValue().getImage());
		displayImage.setEffect(this.getValue().getEffect());
	}

	@Override
	public ImageView display()
	{
		return displayImage;
	}

	@Override
	public Node makeDynamicUpdater()
	{
		Label label = new Label(String.format("Update %s", this.getName()));
		Button input = initializeButton(String.format("Update %s", this.getName()));
		VBox vbox = new VBox();

		vbox.getChildren().add(label);
		vbox.getChildren().add(input);

		vbox.setAlignment(Pos.CENTER);
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
				this.setValue(new Image(selectedFile.toURI().toString()));
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
