package instruction.BooleanOperations;

import instruction.Instruction;
import instruction.InstructionData;
import interpreter.InstructionNode;

public abstract class BooleanOperation extends Instruction {
	private static final int DEFAULT_NUM_ARGS = 2;
	public BooleanOperation(InstructionData instructionData, InstructionNode root) {
		super(instructionData, root);
	}
	//NOT overwrites this
	public int getNumArgs(){
		return DEFAULT_NUM_ARGS;
	}
}
