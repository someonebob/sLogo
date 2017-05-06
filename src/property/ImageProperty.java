package property;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import exceptions.InvalidIndexException;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import util.FileChooserUtil;
import util.ImageViewTuple;
import view.ActorView;

/**
 * 
 * @author jimmy
 *
 */

/*
 * Note from Matthew: Whenever you instantiate a new ImageProperty instance, you
 * MUST call addDefaultImageToInitialImageFilename() after setting the default
 * image, else several Instructions will not work. Ideally, you could do all
 * this in the ImageProperty constructor, but I did not want to modify this
 * class more than necessary.
 * 
 */

public class ImageProperty extends Property<ImageView>
{
	private static final List<ImageViewTuple> INDEXED_IMAGES = new ArrayList<>();
	private static final String TURTLE_IMAGES_LOCATION = "images";
	private static final String RESOURCE_INDEX_NAME = "DefaultIndexMessage";

	private ImageView displayImage;

	static {
		File currentFolder = new File(".");
		File srcFolder = currentFolder.getParentFile();
		File imagesFolder = new File(srcFolder, TURTLE_IMAGES_LOCATION);
		for (File imageFile : imagesFolder.listFiles()) {
			INDEXED_IMAGES.add(new ImageViewTuple(imageFile.getName(), new ImageView(new Image(imageFile.getName()))));
		}
	}

	public ImageProperty(String name)
	{
		super(name);
		displayImage = new ImageView();
		super.setValue(new ImageView());
		displayImage.setFitHeight(ActorView.ACTOR_HEIGHT);
		displayImage.setFitWidth(ActorView.ACTOR_WIDTH);
		displayImage.setPreserveRatio(true);
	}

	public List<ImageViewTuple> getIndexedImages()
	{
		return INDEXED_IMAGES;
	}

	/**
	 * Fixes the .equals() problem. Consult me for details.
	 * 
	 * @author Matthew
	 * @param initialImageFilename
	 */

	public void mergeDuplicateDefaultImages(String initialImageFilename)
	{
		for (ImageViewTuple tuple : INDEXED_IMAGES) {
			if (tuple.getFilename().equals(initialImageFilename)) {
				tuple.setImageView(displayImage);
				return;
			}
		}
		throw new InvalidIndexException(RESOURCE_INDEX_NAME);
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
	public List<Node> makeDynamicUpdaters()
	{
		String s  = String.format("Update %s", this.getName());
		Button input = initializeButton(s);

		return Arrays.asList(input);
	}

	private Button initializeButton(String defaultText)
	{
		Button input = new Button();
		input.setText(defaultText);
		Stage newWindow = new Stage();
		input.setOnAction(e -> {
			File selectedFile = FileChooserUtil.setupFileChooser("IMAGE", "New Image",
					new File(System.getProperty("user.dir") + "/images"), "*.png", "*.gif").showOpenDialog(newWindow);
			if (selectedFile != null) {
				this.setValue(new Image(selectedFile.toURI().toString()));
			}
		});
		return input;
	}

}