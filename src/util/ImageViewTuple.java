package util;

import exceptions.CastingException;
import javafx.scene.image.ImageView;

public class ImageViewTuple {

	private static final String RESOURCE_CASTING_NAME = "CastingMessage";
	private String filename;
	private ImageView imageView;

	public ImageViewTuple(String filename, ImageView imageView) {
		this.filename = filename;
		this.imageView = imageView;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public ImageView getImageView() {
		return imageView;
	}

	public void setImageView(ImageView imageView) {
		this.imageView = imageView;
	}
	
	@Override
	public boolean equals(Object other){
		if(!(other instanceof ImageViewTuple)){
			 throw new CastingException(RESOURCE_CASTING_NAME);
		}
		return imageView.getImage().equals(((ImageViewTuple) other).getImageView().getImage());
	}
}
