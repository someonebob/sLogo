package instruction.MathOperations;

import instruction.InstructionData;
import interpreter.InstructionNode;

public class Sum extends MathOperation{
	private static int NUM_ARGS = 2;
	
	public Sum(InstructionData data, InstructionNode node) {
		super(data, node);
	}
	
	@Override
	public double execute() {
		return getArguments().get(0) + getArguments().get(1);
	}

	@Override
	public int getNumArgs() {
		return NUM_ARGS;
	}

}
