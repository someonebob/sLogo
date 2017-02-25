package instruction.TurtleCommands;

import instruction.InstructionData;
import interpreter.InstructionNode;

public class Forward extends TurtleCommand{
	private static final int NUM_ARGS = 1;
	
	public Forward(){
		super(new InstructionData(), new InstructionNode());
	}
	
	public Forward(InstructionData instructionData, InstructionNode root) {
		super(instructionData, root);
	}

	@Override
	public double execute() {
		double distance = getArgument(0);
		move(distance);
		return distance;
	}

	@Override
	public int getNumArgs() {
		return NUM_ARGS;
	}
}
