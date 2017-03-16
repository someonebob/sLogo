package instruction.TurtleCommands;

import java.util.List;

import exceptions.NonsensicalArgumentException;
import instruction.ActorSpecificInstruction;
import instruction.Instruction;
import instruction.InstructionData;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import util.MathUtil;
import util.PointPolar;
import view.ActorView;

/**
 * Superclass of all turtle command Instructions, which change a turtle's state
 * on the simulation screen (for example, move forward, turn, etc.) It contains
 * helper methods used repeatedly by these Instructions. Implements
 * ActorSpecificInstruction interface since all subclasses are for commands
 * applying only to a single actor. It is assumed that instructionData and args
 * are not null, and that args contains the correct number of non-null entries
 * for each Instruction subclass. Dependencies are InstructionData, List,
 * String, and Instruction's 3-argument constructor.
 * 
 * @author Matthew Barbano
 * @author jimmy
 *
 */
public abstract class TurtleCommand extends Instruction implements ActorSpecificInstruction {
	private static final String RESOURCE_NEGATIVE_PIXELS_NAME = "MoveNegativeMessage";
	private static final String RESOURCE_BOUNDS_NAME = "MoveBoundsMessage";

	/**
	 * Standard 3-argument constructor for the Instruction hierarchy. Through a
	 * series of super() constructor calls up the hierarchy, sets 3
	 * corresponding variables in Instruction. No assumptions cause direct
	 * impact in this constructor. Setting any arguments or entries in args to
	 * null will cause errors elsewhere. Design decision: Making args a List
	 * accommodates SLogo commands with different numbers of arguments.
	 * 
	 * @param instructionData
	 *            for accessing frontend data
	 * @param args
	 *            for storing arguments to SLogo commands
	 * @param myText
	 *            a String representation of this Instruction
	 */
	public TurtleCommand(InstructionData instructionData, List<String> args, String myText) {
		super(instructionData, args, myText);
	}

	/**
	 * Overloaded with move() taking Point2D as a parameter. Computes the new
	 * location coordinates the active actor will have if it moves "distance"
	 * from its current position, and calls the overloaded move() method with
	 * these new coordinates. Dependencies include Point2D and InstructionData.
	 * No assumptions are made about "distance"; it can be zero or negative.
	 * 
	 * @param distance
	 *            to move
	 */
	protected void move(double distance) {
		Point2D currentLocation = getActiveActor().getLocation();
		double currentHeading = getActiveActor().getHeading();
		Point2D deltaVector = MathUtil.polarToRectangular(new PointPolar(distance, currentHeading));
		Point2D newLocation = currentLocation.add(deltaVector);
		move(newLocation);
	}

	/**
	 * Overloaded with move() taking a double as a parameter. Called by the
	 * other move() method, this method first checks that newLocation results in
	 * a position in which the actor is still within the simulation screen and
	 * throws a NonsensicalArgumentException if not. If so, it calls ActorView's
	 * move() method with newLocation to execute the frontend animation. Assumes
	 * newLocation places the actor within the simulation bounds. Dependencies
	 * are Bounds, InstructionData, MathUtil, NonsensicalArgumentException, and
	 * ActorView.
	 * 
	 * @param newLocation
	 *            the new location of active actor
	 * @throws NonsensicalArgumentException
	 *             if actor is placed outside of simulation GUI
	 */
	protected void move(Point2D newLocation) {
		Bounds bounds = getInstructionData().getSimulationBounds();
		if (MathUtil.doubleLessThan(newLocation.getX(), -bounds.getMaxX() / 2)
				|| MathUtil.doubleLessThan(newLocation.getY(), -bounds.getMaxY() / 2)
				|| MathUtil.doubleGreaterThan(newLocation.getX(), bounds.getMaxX() / 2)
				|| MathUtil.doubleGreaterThan(newLocation.getY(), bounds.getMaxY() / 2)) {
			throw new NonsensicalArgumentException(RESOURCE_BOUNDS_NAME);
		}
		getActiveActor().move(newLocation);
	}

	/**
	 * Sets the active actor's heading to newHeading. Dependencies are
	 * ActorView.
	 * 
	 * @param newHeading
	 *            the new heading
	 */
	protected void setHeading(double newHeading) {
		getActiveActor().setHeading(ActorView.STARTING_HEADING + newHeading);
	}

	/**
	 * Rotates the active actor by deltaHeading in the counterclockwise
	 * direction. Dependencies are ActorView.
	 * 
	 * @param deltaHeading
	 *            the change in heading
	 */
	protected void turn(double deltaHeading) {
		getActiveActor().rotate(deltaHeading);
	}

	/**
	 * Intermediate method for moving ActorView to position (x, y). Converts (x,
	 * y) to a Point2D and calls move() with it as an argument. Assumes x and y
	 * do not place active actor out of simulation GUI bounds. Dependencies are
	 * Point2D.
	 * 
	 * @param x
	 *            coordinate
	 * @param y
	 *            coordinate
	 */
	protected void setPosition(double x, double y) {
		move(new Point2D(x, y));
	}

	/**
	 * Returns active actor's current position as Point2D. Dependencies are
	 * Point2D and ActorView.
	 * 
	 * @return current position
	 */
	protected Point2D getPosition() {
		return getActiveActor().getLocation();
	}

	protected void togglePenState() {
		// TODO
	}

	protected void togglePenVisibility() {
		// TODO
	}

	/**
	 * Helper method for exception throwing called in execute() of TurtleCommand
	 * subclasses. Checks if "distance" is negative and throws a
	 * NonsensicalArgumentException if it is. Dependencies are MathUtil and
	 * NonsensicalArgumentException.
	 * 
	 * @param distance
	 *            the value to check
	 * @throws NonsensicalArgumentException
	 *             if distance < 0
	 */
	protected void checkNegativeArgumentException(double distance) {
		if (MathUtil.doubleLessThan(distance, 0.0)) {
			throw new NonsensicalArgumentException(RESOURCE_NEGATIVE_PIXELS_NAME);
		}
	}
}
