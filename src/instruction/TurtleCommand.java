package instruction;

import java.util.List;

import javafx.geometry.Point2D;
import models.Actor;

/**
 * Exploits overloading methods
 * @author Matthew Barbano
 *
 */
public abstract class TurtleCommand extends Instruction{
	public TurtleCommand(InstructionData instructionData, InstructionNode root){
		super(instructionData, root);
	}
	
	public void move(Point2D newLocation){
		getInstructionData().getActiveActor().setLocation(newLocation);
	}
	
	public void move(double distance){
		Point2D currentLocation = getInstructionData().getActiveActor().getLocation();
		
	}
	/*
	public void move(double newX, double newY){
		List<Actor> actorList = getInstructionData().getActors();
		for(Actor actor : actorList){
			actor.setLocation(new Point(newX, newY));
		}
	}
	public void move(double deltaX, double deltaY){
		List<Actor> actorList = getInstructionData().getActors();
		for(Actor actor : actorList){
			move(actor.getLocation().getX() + deltaX, actor.getLocation().getY() + deltaY);
		}
	}
	public void move(double oldX, double oldY, double distance){
		move(oldX, oldY, distance * Math.cos())
	}
	*/
}
