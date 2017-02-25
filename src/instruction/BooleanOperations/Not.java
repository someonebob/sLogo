package instruction.BooleanOperations;

import instruction.InstructionData;
import interpreter.InstructionNode;

public class Not extends BooleanOperation{
	public Not(InstructionData data, InstructionNode node){
		super(data, node);
	}
	@Override
	public double execute() {
		return getArgumentDouble(0) == 0 ? 1 : 0;
	}
}
