package instruction.TurtleQueries;

import java.util.List;

import instruction.InstructionData;

public class XCoordinate extends TurtleQuery
{

	public XCoordinate(InstructionData instructionData, List<String> args, String myText)
	{
		super(instructionData, args, myText);
	}

	@Override
	public double execute()
	{
		return getActiveActor().getLocation().getX();
	}
}
