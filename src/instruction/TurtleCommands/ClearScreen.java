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
		if (this.getActiveActor() instanceof TurtleView) {
			// TODO: penup doesn't actually put the pen up
			this.getActiveActor().move(new Point2D(0, 0));
			setPosition(0, 0);
			this.getActiveActor().getPen().clear();
			this.getActiveActor().getPen().penDown();
		}
		return distanceTraveled;
	}
}
