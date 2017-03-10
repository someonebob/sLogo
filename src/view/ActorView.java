package view;
import java.util.Arrays;
import java.util.List;

import javafx.animation.Animation;
import javafx.animation.SequentialTransition;


import javafx.animation.Transition;

import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import main.Defaults;
import property.ActorPositionProperty;
import property.HeadingProperty;
import property.ImageColorProperty;
import property.ImageProperty;
import property.Property;
import property.SpeedProperty;
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

	private ImageProperty image;
	private ImageColorProperty imageColor;
	private ActorPositionProperty actorPosition;
	private HeadingProperty heading;
	private SequentialTransition actorMove;
	private ID id;
	protected SpeedProperty speed;
	private boolean told = true;

	public ActorView(Defaults defaults, int id)
	{
		image = new ImageProperty("Actor Image");
		imageColor = new ImageColorProperty("Actor Image Color", image);
		speed = new SpeedProperty("FPS");
		speed.setValue(5.0);
		actorPosition = new ActorPositionProperty("Actor Position", this);
		heading = new HeadingProperty("Actor Heading", this);
		this.id = new ID(id);
		

		actorMove = new SequentialTransition();
		actorMove.setNode(this.getImageView());
		// scale the image
		image.getValue().setFitHeight(ACTOR_HEIGHT);
		image.getValue().setFitWidth(ACTOR_WIDTH);
		image.getValue().setPreserveRatio(true);
		loadImage(defaults.image());
		// start facing up
		this.setHeading(STARTING_HEADING);
		// // initial rotation
		actorMove.play();
	}
	public boolean isTold(){
		return told;
	}
	
	public void setTold(){
		told = true;
	}
	public void setUntold(){
		told = false;
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

	public void move(Point2D point)
	{
		actorPosition.setValue(point);
	}

	public Point2D getLocation()
	{
		return actorPosition.getValue();
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
		this.image.mergeDuplicateDefaultImages(stringImage);
	}

	public void addTransition(Transition transition)
	{

		for (Animation trans : actorMove.getChildren()) {
			//System.out.print(trans);
		}
		transition.setOnFinished(e -> {
			actorMove.getChildren().remove(transition);
		});
		actorMove.getChildren().add(transition);
	}

	public void setHeading(double newHeading)
	{
		heading.setValue(newHeading);
	}

	public void rotate(double rotateAngle)
	{
		setHeading(heading.getValue() + rotateAngle);
	}


	public double getHeading()
	{
		return heading.getValue();
	}
	public List<Property<?>> getProperties()
	{
		return Arrays.asList(image, imageColor, actorPosition, heading, speed);
	}
	
	public double getSpeed(){
		return speed.getValue();
	}

}