package instruction.TurtleCommands;

import java.util.List;

import instruction.InstructionData;
import util.MathUtil;

/**
 * 
 * @author jimmy
 *
 */
public class Home extends TurtleCommand
{

	public Home(InstructionData instructionData, List<String> args, String myText)
	{
		super(instructionData, args, myText);
	}

	@Override
	public double execute()
	{
		double distanceTraveled = MathUtil.distance(getPosition().getX(), getPosition().getY());
		setPosition(0, 0);
		return distanceTraveled;
	}
}
