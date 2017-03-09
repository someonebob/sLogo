package user_structures;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * An ImageView subclass with a filename String field, which is 
 * very hard to access via JavaFX's current implementation.
 * Recommended by http://stackoverflow.com/questions/25123115/get-image-path-javafx
 * @author Matthew Barbano
 *
 */
public class NamedImageWrapper{
	private String filename;
	private ImageView imageView;
	
	public NamedImageWrapper() {
		imageView = new ImageView();
		//imageView = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(filename)));
	}
	
	public NamedImageWrapper(String filename) {
		//imageView = new ImageView();
		imageView = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(filename)));
		this.filename = filename;
	}

	public String getFilename(){
		return filename;
	}
	
	public ImageView getImageView(){
		return imageView;
	}
	
	public void setImage(ImageView image)
	{
		imageView.setImage(image.getImage());
	}
	
	public boolean equals(NamedImageWrapper other){
		return filename.equals(other.getFilename());
	}
}
