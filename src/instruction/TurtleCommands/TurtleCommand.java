package instruction.TurtleCommands;

import java.util.List;

import exceptions.NonsensicalArgumentException;
import instruction.Instruction;
import instruction.InstructionData;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import util.MathUtil;
import util.PointPolar;
import view.ActorView;

/**
 * This class is the abstract superclass for all Instructions which change a
 * turtle's state on the simulation screen (for example, move forward, turn,
 * etc.) It contains helper methods used repeatedly by these Instructions.
 * 
 * @author Matthew Barbano
 * @author jimmy
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
		Point2D currentLocation = getActiveTurtle().getLocation();
		double currentHeading = getActiveTurtle().getHeading();
		Point2D deltaVector = MathUtil.polarToRectangular(new PointPolar(distance, currentHeading));
		Point2D newLocation = currentLocation.add(deltaVector);
		move(newLocation);
	}

	protected void move(Point2D newLocation)
	{
		Bounds bounds = getInstructionData().getSimulationBounds();
		if (MathUtil.doubleLessThan(newLocation.getX(), -bounds.getMaxX() / 2)
				|| MathUtil.doubleLessThan(newLocation.getY(), -bounds.getMaxY() / 2)
				|| MathUtil.doubleGreaterThan(newLocation.getX(), bounds.getMaxX() / 2)
				|| MathUtil.doubleGreaterThan(newLocation.getY(), bounds.getMaxY() / 2)) {
			throw new NonsensicalArgumentException(RESOURCE_BOUNDS_NAME);
		}
		getActiveTurtle().move(newLocation);
	}

	protected void setHeading(double newHeading)
	{
		getActiveTurtle().setHeading(ActorView.STARTING_HEADING + newHeading);
	}

	protected void turn(double deltaHeading)
	{
		getActiveTurtle().rotate(deltaHeading);
	}

	protected void setPosition(double x, double y)
	{
		move(new Point2D(x, y));
	}

	protected Point2D getPosition()
	{
		return getActiveTurtle().getLocation();
	}

	protected void togglePenState()
	{
		// TODO
	}

	protected void togglePenVisibility()
	{
		// TODO
	}

	protected void checkNegativeArgumentException(double distance)
	{
		if (MathUtil.doubleLessThan(distance, 0.0)) {
			throw new NonsensicalArgumentException(RESOURCE_NEGATIVE_PIXELS_NAME);
		}
	}
}
