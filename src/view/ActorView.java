package view;

import java.util.Arrays;
import java.util.List;
import java.util.Observable;

import exceptions.InvalidIndexException;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.util.Duration;
import main.Defaults;
import models.Actor;
import property.ActorPositionProperty;
import property.ImageColorProperty;
import property.ImageProperty;
import property.Property;
import user_structures.ID;
import user_structures.NamedImageWrapper;
import util.MathUtil;
/**
 * 
 * @author jimmy
 * @author Jesse
 * @author Matthew Barbano
 *
 */
public abstract class ActorView implements View
{
	public static final int ACTOR_HEIGHT = 75;
	public static final int ACTOR_WIDTH = 75;
	public static final int STARTING_HEADING = -90;
	public static final String RESOURCE_DOUBLE_NAME = "DoubleIndexMessage";
	public static final String RESOURCE_BOUNDS_NAME = "IndexOutOfBoundsMessage";
	public static final String RESOURCE_NOT_FOUND_NAME = "ElementNotFoundMessage";
	// TODO: Make stack of animations to run, and run them 1 at a time.
	// TODO: Update image so that it
	private Actor actor;
	private ImageProperty image;
	private ImageColorProperty imageColor;
	private ActorPositionProperty actorPosition;
	private SequentialTransition actorMove;
	private ID id;
	public ActorView(Defaults defaults, int id)
	{
		// populate availableImages by subclass
		availableImages = new ArrayList<>();
		populateAvailableImages();
		// scale all the images
		for(NamedImageWrapper possibleImage : availableImages){
			possibleImage.getImageView().setFitHeight(ACTOR_HEIGHT);
			possibleImage.getImageView().setPreserveRatio(true);
		}
		// load the default image
		image = new NamedImageWrapper(defaults.image());
		//loadImage(defaults.image());
		
		actor = new Actor();
		image = new ImageProperty("Actor Image");
		imageColor = new ImageColorProperty("Actor Image Color", image);
		actorPosition = new ActorPositionProperty("Actor Position", this);
		this.id = new ID(id);
		actorMove = new SequentialTransition();
		actorMove.setNode(this.getImage());
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
	
	protected abstract void populateAvailableImages();
	
	public List<NamedImageWrapper> getAvailableImages(){
		return availableImages;
	}
	
	
	public void setImageByIndex(double index){
		checkValidIndex(index, availableImages.size());
		setImage(availableImages.get((int)index));
	}
	public int getImageByIndex(){
		int index = availableImages.indexOf(getImage());
		if(index != -1){
			return index;
		}
		else{
			throw new InvalidIndexException(RESOURCE_NOT_FOUND_NAME);
		}
	}
	
	private void checkValidIndex(double index, int size){
		if(!MathUtil.hasIntegerValue(index)){
			throw new InvalidIndexException(RESOURCE_DOUBLE_NAME);
		}
		if(index < 0.0 || index >= size){
			throw new InvalidIndexException(RESOURCE_BOUNDS_NAME);
		}
	}
	
	public ID getID()
	{
		return id;
	}
	public void step()
	{
		actorMove.play();
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
	}
	public Actor getActor()
	{
		return actor;
	}
	public void setActor(Actor actor)
	{
		this.actor = actor;
	}
	public void setImage(NamedImageWrapper image)
	{
		this.image.setValue(image);
	}
	public NamedImageWrapper getImage()
	{
		return image.getValue();
	}
	private void loadImage(String stringImage)
	{
		int index = 0;
		for(NamedImageWrapper imageElement : availableImages){
			if(stringImage.equals(imageElement.getFilename())){
				this.setImage(availableImages.get(index));
				return; 
			}
			index++;
		}
		throw new InvalidIndexException(RESOURCE_NOT_FOUND_NAME);
		
		//setImage(new NamedImageWrapper(stringImage));  - Caution: This is wrong - need the actual object in availableImages, not a copy
	}
	public void move(Point2D newLocation)
	{
		TranslateTransition move = new TranslateTransition(Duration.millis(500));
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
		RotateTransition rotate = new RotateTransition(Duration.millis(200));
		rotate.setFromAngle(this.actor.getHeading());
		rotate.setToAngle(newHeading);
		rotate.setCycleCount(1);
		rotate.setOnFinished(e -> {
			actorMove.getChildren().remove(rotate);
		});
		actorMove.getChildren().add(rotate);
		actor.setHeading(newHeading);
	}
	public double getHeading()
	{
		return actor.getHeading();
	}

	public List<Property<?>> getProperties()
	{
		return Arrays.asList(image, imageColor, actorPosition);
	}

}