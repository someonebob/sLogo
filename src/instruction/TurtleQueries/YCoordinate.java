package instruction.TurtleQueries;

import java.util.List;

import instruction.InstructionData;

public class YCoordinate extends TurtleQuery
{

	public YCoordinate(InstructionData instructionData, List<String> args)
	{
		super(instructionData, args);
	}

	@Override
	public double execute()
	{
		return getActiveActor().getActor().getLocation().getY();
	}

}
