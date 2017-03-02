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
	public static final int ACTOR_HEIGHT = 75;
	public static final int STARTING_HEADING = -90;

	private Actor actor;
	private ImageView image;

	public ActorView(String imageString)
	{
		actor = new Actor();
		image = new ImageView();
		// scale the image
		image.setFitHeight(ACTOR_HEIGHT);
		image.setPreserveRatio(true);
		loadImage(imageString);
		// start facing up
		this.setHeading(STARTING_HEADING);
	}

	@Override
	public Node display()
	{
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
		view.setFitHeight(ACTOR_HEIGHT);
		view.setPreserveRatio(true);
		this.image = view;
		this.setHeading(STARTING_HEADING);
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

	public void move(Point2D newLocation)
	{
		final Timeline timeline = new Timeline();
		timeline.setCycleCount(1);
		Point2D deltaLocation = newLocation.subtract(this.getActor().getLocation());
		final KeyValue xkv = new KeyValue(image.translateXProperty(), image.getTranslateX() + deltaLocation.getX());
		final KeyValue ykv = new KeyValue(image.translateYProperty(), image.getTranslateY() + deltaLocation.getY());
		final KeyFrame kf = new KeyFrame(Duration.millis(500), xkv, ykv);
		timeline.getKeyFrames().add(kf);
		timeline.play();
		actor.setLocation(newLocation);
	}

	public void moveWithoutDrawing(Point2D newLocation)
	{
		image.translateXProperty().set(0);
		image.translateYProperty().set(0);
		actor.setLocation(newLocation);
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
