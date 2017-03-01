package instruction.CoreInstructions;

import instruction.InstructionData;
import interpreter.InstructionNode;

public class If extends CoreInstruction {
	
	public If(InstructionData instructionData, InstructionNode root) {
		super(instructionData, root);
	}

	@Override
	public double execute(){
		if(getArgumentDouble(0) != 0){
			return runListCommands(1);
		}
		return 0;
	}

	

}
