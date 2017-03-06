package view;

import java.util.Observable;

import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.control.SelectionModel;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import main.Defaults;
import models.Actor;
import user_structures.ID;

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
	// TODO: Make stack of animations to run, and run them 1 at a time.
	// TODO: Update image so that it

	private Actor actor;
	private ImageView image;
	private SequentialTransition actorMove;
	private ID id;

	public ActorView(Defaults defaults, int id)
	{
		actor = new Actor();
		image = new ImageView();
		this.id = new ID(id);

		actorMove = new SequentialTransition();
		actorMove.setNode(this.getImage());
		// scale the image
		image.setFitHeight(ACTOR_HEIGHT);
		image.setPreserveRatio(true);
		loadImage(defaults.image());
		// start facing up
		this.setHeading(STARTING_HEADING);
		// initial rotation
		actorMove.play();
	}
	
	public ID getID(){
		return id;
	}

	public void step()
	{
		actorMove.play();
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
		// ImageView view = new ImageView(image);
		// view.setFitHeight(ACTOR_HEIGHT);
		// view.setPreserveRatio(true);
		// this.setHeading(STARTING_HEADING);
		this.image.setImage(image);
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
		image.translateXProperty().set(0);
		image.translateYProperty().set(0);
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

}
