package instruction.TurtleCommands;

import instruction.InstructionData;
import interpreter.InstructionNode;

public class Forward extends TurtleCommand{
	
	public Forward(InstructionData instructionData, InstructionNode root) {
		super(instructionData, root);
	}

	@Override
	public double execute() {
		double distance = getArgumentDouble(0);
		move(distance);
		return distance;
	}
}
