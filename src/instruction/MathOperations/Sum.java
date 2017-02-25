package instruction.MathOperations;

import instruction.InstructionData;
import interpreter.InstructionNode;

public class Sum extends MathOperation{
	private static int NUM_ARGS = 2;
	
	public Sum(){
		super(new InstructionData(), new InstructionNode());
	}
	public Sum(InstructionData data, InstructionNode node) {
		super(data, node);
	}
	
	@Override
	public double execute() {
		return getArgument(0) + getArgument(1);
	}

	@Override
	public int getNumArgs() {
		return NUM_ARGS;
	}

}
