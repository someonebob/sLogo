package instruction.TurtleQueries;

import java.util.List;

import instruction.InstructionData;

public class XCoordinate extends TurtleQuery{
	
	public XCoordinate(InstructionData instructionData,  List<String> args) {
		super(instructionData, args);
	}

	@Override
	public double execute() {
		return getActiveActor().getLocation().getX();
	}
}
