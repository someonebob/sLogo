package instruction.TurtleCommands;

import java.util.List;

import instruction.InstructionData;

/**
 * 
 * @author jimmy
 *
 */
public class Left extends TurtleCommand
{

	public Left(InstructionData instructionData, List<String> args, String myText)
	{
		super(instructionData, args, myText);
	}

	@Override
	public double execute()
	{
		double degree = getArgumentDouble(0);
		turn(degree);
		return degree;
	}
}
