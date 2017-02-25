package view;

import java.util.Observable;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ActorView implements View
{

	public static final String DEFAULT_IMAGE = "Squirt.png";

	private ImageView actor;

	public ActorView()
	{
		actor = new ImageView();
		// scale the image
		actor.setFitHeight(50);
		actor.setPreserveRatio(true);
		loadImage();
	}

	@Override
	public Node display()
	{
		// TODO Auto-generated method stub
		return actor;
	}

	@Override
	public void update(Observable o, Object arg)
	{

	}

	@Override
	public void updateData(String arg)
	{
		// TODO Auto-generated method stub

	}

	public void setImage(Image image)
	{
		actor.setImage(image);
	}

	private void loadImage()
	{
		Image image = new Image(getClass().getClassLoader().getResourceAsStream(DEFAULT_IMAGE));
		this.setImage(image);
	}

}
