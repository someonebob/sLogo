package instruction.TurtleCommands;

import java.util.List;

import instruction.InstructionData;
import javafx.geometry.Point2D;
import util.MathUtil;
import view.TurtleView;

/**
 * 
 * @author jimmy
 *
 */
public class ClearScreen extends TurtleCommand
{

	public ClearScreen(InstructionData instructionData, List<String> args, String myText)
	{
		super(instructionData, args, myText);
	}

	@Override
	public double execute()
	{
		double distanceTraveled = MathUtil.distance(getPosition().getX(), getPosition().getY());
		if (this.getActiveTurtle() instanceof TurtleView) {
			// TODO: penup doesn't actually put the pen up
			this.getActiveTurtle().move(new Point2D(0, 0));
			setPosition(0, 0);
			this.getActiveTurtle().getPen().clear();
			this.getActiveTurtle().getPen().penDown();
		}
		return distanceTraveled;
	}
}
