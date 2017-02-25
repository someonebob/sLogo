package instruction.TurtleCommands;

import instruction.Instruction;
import instruction.InstructionData;
import interpreter.InstructionNode;
import javafx.geometry.Point2D;
import util.MathUtility;
import util.PointPolar;

/**
 * This class is the abstract superclass for all Instructions which change
 * a turtle's state on the simulation screen (for example, move forward, turn, etc.)
 * It contains helper methods used repeatedly by these Instructions.
 * @author Matthew Barbano
 *
 */
public abstract class TurtleCommand extends Instruction{
	public TurtleCommand(InstructionData instructionData, InstructionNode root){
		super(instructionData, root);
	}
	
	protected void moveNewLocation(Point2D newLocation){
		getActiveActor().setLocation(newLocation);
	}
	
	protected void move(double distance){
		Point2D currentLocation = getActiveActor().getLocation();
		double currentHeading = getActiveActor().getHeading();
		Point2D deltaVector = MathUtility.polarToRectangular(new PointPolar(distance, currentHeading));
		moveNewLocation(currentLocation.add(deltaVector));
	}
	
	protected void turnNewHeading(double newHeading){
		getActiveActor().setHeading(newHeading);
	}
	
	protected void turn(double deltaHeading){
		turnNewHeading(getActiveActor().getHeading() + deltaHeading);
	}
	
	protected void togglePenState(){
		//TODO
	}
	
	protected void togglePenVisibility(){
		//TODO
	}
}
