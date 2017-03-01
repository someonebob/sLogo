package instruction.TurtleCommands;

import java.util.List;

import instruction.InstructionData;

/**
 * 
 * @author jimmy
 *
 */
public class SetHeading extends TurtleCommand
{

	public SetHeading(InstructionData instructionData, List<String> args, String myText)
	{
		super(instructionData, args, myText);
	}

	@Override
	public double execute()
	{
		double degree = getArgumentDouble(0);
		setHeading(degree);
		return degree;
	}
}
