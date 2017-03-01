package instruction.TurtleCommands;

import instruction.InstructionData;
import interpreter.InstructionNode;

public class Backward extends TurtleCommand{
	
	public Backward(InstructionData instructionData, InstructionNode root) {
		super(instructionData, root);
	}

	@Override
	public double execute() {
		double distance = getArgumentDouble(0);
		move(-distance);
		return distance;
	}
}
