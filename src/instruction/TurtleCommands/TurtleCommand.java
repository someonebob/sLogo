package instruction.TurtleCommands;

import java.util.List;

import exceptions.NonsensicalArgumentException;
import instruction.Instruction;
import instruction.InstructionData;
import javafx.geometry.Point2D;
import util.MathUtility;
import util.PointPolar;
import view.PageView;

/**
 * This class is the abstract superclass for all Instructions which change a
 * turtle's state on the simulation screen (for example, move forward, turn,
 * etc.) It contains helper methods used repeatedly by these Instructions.
 * 
 * @author Matthew Barbano
 *
 */
public abstract class TurtleCommand extends Instruction {
	private static final String RESOURCE_NEGATIVE_PIXELS_NAME = "MoveNegativeMessage";
	private static final String RESOURCE_BOUNDS_NAME = "MoveBoundsMessage";
	
	public TurtleCommand(InstructionData instructionData, List<String> args) {
		super(instructionData, args);
	}

	protected void moveNewLocation(Point2D newLocation) { // TODO ask Jimmy
															// about PageView
															// syntax
		if (MathUtility.doubleLessThan(newLocation.getX(), 0.0) 
				|| MathUtility.doubleLessThan(newLocation.getY(), 0.0)
				|| MathUtility.doubleGreaterThan(newLocation.getX(), PageView.WIDTH)
				|| MathUtility.doubleGreaterThan(newLocation.getY(), PageView.HEIGHT)) {
			throw new NonsensicalArgumentException(RESOURCE_BOUNDS_NAME);
		}
		getActiveActor().setLocation(newLocation);
	}

	protected void move(double distance) {
		if (MathUtility.doubleLessThan(distance, 0.0)) {
			throw new NonsensicalArgumentException(RESOURCE_NEGATIVE_PIXELS_NAME);
		}
		Point2D currentLocation = getActiveActor().getLocation();
		double currentHeading = getActiveActor().getHeading();
		Point2D deltaVector = MathUtility.polarToRectangular(new PointPolar(distance, currentHeading));
		moveNewLocation(currentLocation.add(deltaVector));

	}

	protected void turnNewHeading(double newHeading) {
		getActiveActor().setHeading(newHeading);
	}

	protected void turn(double deltaHeading) {
		turnNewHeading(getActiveActor().getHeading() + deltaHeading);
	}

	protected void togglePenState() {
		// TODO
	}

	protected void togglePenVisibility() {
		// TODO
	}
}
