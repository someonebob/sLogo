package instruction.TurtleQueries;

import instruction.InstructionData;
import interpreter.InstructionNode;

public class Heading extends TurtleQuery {

	public Heading(InstructionData instructionData, InstructionNode root) {
		super(instructionData, root);
	}

	@Override
	public double execute() {
		return getInstructionData().getActiveActor().getHeading();
	}

}
