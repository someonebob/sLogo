package instruction.CoreInstructions;

import instruction.Instruction;
import instruction.InstructionData;
import interpreter.InstructionNode;

public abstract class CoreInstruction extends Instruction {
	
	public CoreInstruction(InstructionData instructionData, InstructionNode root) {
		super(instructionData, root);
	}
}
