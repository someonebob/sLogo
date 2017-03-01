package instruction.TurtleCommands;

import java.util.List;

import instruction.InstructionData;
import util.MathUtil;

/**
 * 
 * @author jimmy
 *
 */
public class SetPosition extends TurtleCommand
{

	public SetPosition(InstructionData instructionData, List<String> args, String myText)
	{
		super(instructionData, args, myText);
	}

	@Override
	public double execute()
	{
		double x = getArgumentDouble(0);
		double y = getArgumentDouble(1);
		setPosition(x, y);
		return MathUtil.distance(getPosition().getX() - x, getPosition().getY() - y);
	}
}
