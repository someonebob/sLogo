package instruction.TurtleQueries;

import instruction.InstructionData;
import interpreter.InstructionNode;

public class Heading extends TurtleQuery {
	public Heading(){
		super(new InstructionData(), new InstructionNode());
	}
	public Heading(InstructionData instructionData, InstructionNode root) {
		super(instructionData, root);
	}

	@Override
	public double execute() {
		return getActiveActor().getHeading();
	}

}
