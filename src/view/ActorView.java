package view;

import java.util.Observable;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
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
		actor = new Actor();
		image = new ImageView();
		// scale the image
		image.setFitHeight(50);
		image.setPreserveRatio(true);
		loadImage(imageString);
		// image.setX(actor.getLocation().getX());
		// image.setY(actor.getLocation().getY());
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

	public void move(Point2D deltaLocation)
	{
		final Timeline timeline = new Timeline();
		timeline.setCycleCount(1);
		final KeyValue xkv = new KeyValue(image.translateXProperty(), image.getTranslateX() + deltaLocation.getX());
		final KeyValue ykv = new KeyValue(image.translateYProperty(), image.getTranslateY() + deltaLocation.getY());
		final KeyFrame kf = new KeyFrame(Duration.millis(500), xkv, ykv);
		timeline.getKeyFrames().add(kf);
		timeline.play();
		actor.setLocation(actor.getLocation().add(deltaLocation));
	}

	public void setHeading(double newHeading)
	{
		image.setRotate(newHeading);
		actor.setHeading(newHeading);
	}

	public double getHeading()
	{
		return actor.getHeading();
	}

}
