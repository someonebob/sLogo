package instruction.BooleanOperations;

import instruction.Instruction;
import instruction.InstructionData;
import interpreter.InstructionNode;

public abstract class BooleanOperation extends Instruction {
	public BooleanOperation(InstructionData instructionData, InstructionNode root) {
		super(instructionData, root);
	}
}
