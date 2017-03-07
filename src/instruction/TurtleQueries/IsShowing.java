package instruction.TurtleQueries;

import java.util.List;

import instruction.InstructionData;

public class IsShowing extends TurtleQuery
{

	public IsShowing(InstructionData instructionData, List<String> args, String myText)
	{
		super(instructionData, args, myText);
	}

	@Override
	public double execute()
	{
		return getActiveActor().getImage().isVisible() ? 1 : 0;
	}

}
