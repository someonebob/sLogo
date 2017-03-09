package instruction.TurtleQueries;

import java.util.List;

import instruction.InstructionData;

public class YCoordinate extends TurtleQuery
{

	public YCoordinate(InstructionData instructionData, List<String> args, String myText)
	{
		super(instructionData, args, myText);
	}

	@Override
	public double execute()
	{
		return getActiveActor().getLocation().getY();
	}

}
