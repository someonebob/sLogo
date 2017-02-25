package instruction.MathOperations;

import instruction.InstructionData;
import interpreter.InstructionNode;

public class Sum extends MathOperation{
	public Sum(InstructionData data, InstructionNode node) {
		super(data, node);
	}
	
	@Override
	public double execute() {
		return getArgumentDouble(0) + getArgumentDouble(1);
	}

}
