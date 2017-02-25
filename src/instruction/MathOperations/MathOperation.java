package instruction.MathOperations;

import instruction.Instruction;
import instruction.InstructionData;
import interpreter.InstructionNode;

public abstract class MathOperation extends Instruction {
	
	public MathOperation(){
		super(new InstructionData(), new InstructionNode());
		//TODO: check and complete
	}
	
	public MathOperation(InstructionData data, InstructionNode node) {
		super(data, node);
	}
}
