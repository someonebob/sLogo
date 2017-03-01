package view;

import java.util.Observable;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import models.Actor;

/**
 * 
 * @author jimmy
 * @author Jesse
 *
 */
public abstract class ActorView implements View
{
	private Actor actor;
	private ImageView image;

	public ActorView(String imageString)
	{
		image = new ImageView();
		// scale the image
		image.setFitHeight(50);
		image.setPreserveRatio(true);
		actor = new Actor();
		loadImage(imageString);
	}

	@Override
	public Node display()
	{
		// TODO Auto-generated method stub
		return image;
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

	public Actor getActor()
	{
		return actor;
	}

	public void setActor(Actor actor)
	{
		this.actor = actor;
	}

	public void setImage(Image image)
	{
		ImageView view = new ImageView(image);
		view.setFitHeight(50);
		view.setPreserveRatio(true);
		this.image = view;
	}

	public ImageView getImage()
	{
		return image;
	}

	private void loadImage(String stringImage)
	{
		Image image = new Image(getClass().getClassLoader().getResourceAsStream(stringImage));
		this.setImage(image);
	}

}
