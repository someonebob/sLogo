package instruction.TurtleQueries;

import instruction.InstructionData;
import interpreter.InstructionNode;

public class XCoordinate extends TurtleQuery{

	public XCoordinate(InstructionData instructionData, InstructionNode root) {
		super(instructionData, root);
	}

	@Override
	public double execute() {
		return getInstructionData().getActiveActor().getLocation().getX();
	}
}
