package view;

import java.util.Arrays;
import java.util.List;
import java.util.Observable;

import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import main.Defaults;
import models.Actor;
import property.ActorPositionProperty;
import property.HeadingProperty;
import property.ImageColorProperty;
import property.ImageProperty;
import property.Property;
import tool.AnimationControlToolButtons.AnimationSlider;
import user_structures.ID;

/**
 * 
 * @author jimmy
 * @author Jesse
 *
 */
public abstract class ActorView implements View, Cloneable
{
	public static final int ACTOR_HEIGHT = 75;
	public static final int ACTOR_WIDTH = 75;
	public static final int STARTING_HEADING = -90;
	// TODO: Make stack of animations to run, and run them 1 at a time.
	// TODO: Update image so that it

	private Actor actor;
	private ImageProperty image;
	private ImageColorProperty imageColor;
	private ActorPositionProperty actorPosition;
	private HeadingProperty heading;
	// private SpeedProperty speed;
	private SequentialTransition actorMove;
	private ID id;
	private DoubleProperty FPS;
	private DoubleProperty millisecondDelay;

	public ActorView(Defaults defaults, int id)
	{
		actor = new Actor();
		image = new ImageProperty("Actor Image");
		imageColor = new ImageColorProperty("Actor Image Color", image);
		actorPosition = new ActorPositionProperty("Actor Position", this);
		heading = new HeadingProperty("Actor Heading", this);
		this.id = new ID(id);
		FPS = new SimpleDoubleProperty();
		millisecondDelay = new SimpleDoubleProperty();
		FPS.setValue(50);
		millisecondDelay.bind(FPS.multiply(10));

		actorMove = new SequentialTransition();
		actorMove.setNode(this.getImageView());
		// scale the image
		image.getValue().setFitHeight(ACTOR_HEIGHT);
		image.getValue().setFitWidth(ACTOR_WIDTH);
		image.getValue().setPreserveRatio(true);
		loadImage(defaults.image());
		// start facing up
		this.setHeading(STARTING_HEADING);
		// initial rotation
		actorMove.play();
	}

	public ID getID()
	{
		return id;
	}

	public void step()
	{
		actorMove.play();
	}

	public ImageProperty getImageProperty()
	{
		return image;
	}

	public ImageColorProperty getImageColorProperty()
	{
		return imageColor;
	}

	public ActorPositionProperty getActorPositionProperty()
	{
		return actorPosition;
	}

	@Override
	public Node display()
	{
		return image.getValue();
	}

	@Override
	public void update(Observable o, Object arg)
	{
		if(o instanceof AnimationSlider){
			FPS.setValue((Double) arg);
		}
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
		this.image.setValue(image);
	}

	public ImageView getImageView()
	{
		return image.getValue();
	}

	private void loadImage(String stringImage)
	{
		Image image = new Image(getClass().getClassLoader().getResourceAsStream(stringImage));
		this.setImage(image);
	}

	public void move(Point2D newLocation)
	{
		TranslateTransition move = new TranslateTransition(Duration.millis(millisecondDelay.get()));
		move.setFromX(actor.getLocation().getX());
		move.setToX(newLocation.getX());
		move.setFromY(actor.getLocation().getY());
		move.setToY(newLocation.getY());
		move.setCycleCount(1);
		move.setOnFinished(e -> {
			actorMove.getChildren().remove(move);
		});

		actorMove.getChildren().add(move);
		actor.setLocation(newLocation);
	}

	public void moveWithoutDrawing(Point2D newLocation)
	{
		image.getValue().translateXProperty().set(0);
		image.getValue().translateYProperty().set(0);
		actor.setLocation(newLocation);
	}

	public void setHeading(double newHeading)
	{
		makeRotateTransition(this.getHeading(), newHeading);
		actor.setHeading(newHeading);
	}

	public void rotate(double rotateAngle)
	{
		makeRotateTransition(this.actor.getHeading(), this.actor.getHeading() + rotateAngle);
		actor.setHeading(actor.getHeading() + rotateAngle);
	}

	private void makeRotateTransition(double startAngle, double endAngle)
	{
		RotateTransition rotate = new RotateTransition(Duration.millis(200));
		rotate.setFromAngle(startAngle);
		rotate.setToAngle(endAngle);
		rotate.setCycleCount(1);
		rotate.setOnFinished(e -> {
			actorMove.getChildren().remove(rotate);
		});
		actorMove.getChildren().add(rotate);
	}

	public double getHeading()
	{
		return actor.getHeading();
	}

	public List<Property<?>> getProperties()
	{
		return Arrays.asList(image, imageColor, actorPosition, heading);
	}

}
