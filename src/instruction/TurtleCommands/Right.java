package instruction.TurtleCommands;

import java.util.List;

import instruction.InstructionData;

public class Right extends TurtleCommand
{

	public Right(InstructionData instructionData, List<String> args, String myText)
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
