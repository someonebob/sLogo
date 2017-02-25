package instruction.CoreInstructions;

import instruction.InstructionData;
import interpreter.InstructionNode;

public class If extends CoreInstruction {
	public If(){
		super(new InstructionData(), new InstructionNode());
	}
	
	public If(InstructionData instructionData, InstructionNode root) {
		super(instructionData, root);
	}

	@Override
	public double execute() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getNumArgs() {
		// TODO Auto-generated method stub
		return 0;
	}

}
