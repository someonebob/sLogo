package instruction.TurtleCommands;

import java.util.List;

import exceptions.NonsensicalArgumentException;
import instruction.Instruction;
import instruction.InstructionData;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import util.MathUtil;
import util.PointPolar;

/**
 * This class is the abstract superclass for all Instructions which change a
 * turtle's state on the simulation screen (for example, move forward, turn,
 * etc.) It contains helper methods used repeatedly by these Instructions.
 * 
 * @author Matthew Barbano
 *
 */
public abstract class TurtleCommand extends Instruction
{
	private static final String RESOURCE_NEGATIVE_PIXELS_NAME = "MoveNegativeMessage";
	private static final String RESOURCE_BOUNDS_NAME = "MoveBoundsMessage";

	public TurtleCommand(InstructionData instructionData, List<String> args, String myText)
	{
		super(instructionData, args, myText);
	}

	protected void move(double distance)
	{
		if (MathUtil.doubleLessThan(distance, 0.0)) {
			throw new NonsensicalArgumentException(RESOURCE_NEGATIVE_PIXELS_NAME);
		}
		Point2D currentLocation = getActiveActor().getActor().getLocation();
		double currentHeading = getActiveActor().getActor().getHeading();
		Point2D deltaVector = MathUtil.polarToRectangular(new PointPolar(distance, currentHeading));

		Point2D newLocation = currentLocation.add(deltaVector);
		Bounds bounds = getInstructionData().getSimulationBounds();
		if (MathUtil.doubleLessThan(newLocation.getX(), bounds.getMinX())
				|| MathUtil.doubleLessThan(newLocation.getY(), bounds.getMinY())
				|| MathUtil.doubleGreaterThan(newLocation.getX(), bounds.getMaxX())
				|| MathUtil.doubleGreaterThan(newLocation.getY(), bounds.getMaxY())) {
			throw new NonsensicalArgumentException(RESOURCE_BOUNDS_NAME);
		}

		getActiveActor().move(deltaVector); // This method sets this actor's
											// location field, and handles the
											// animation
	}

	protected void turnNewHeading(double newHeading)
	{
		getActiveActor().setHeading(newHeading);
	}

	protected void turn(double deltaHeading)
	{
		turnNewHeading(getActiveActor().getHeading() + deltaHeading);
	}

	protected void togglePenState()
	{
		// TODO
	}

	protected void togglePenVisibility()
	{
		// TODO
	}
}
