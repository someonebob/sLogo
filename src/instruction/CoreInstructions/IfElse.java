package instruction.CoreInstructions;

import instruction.InstructionData;
import interpreter.InstructionNode;

public class IfElse extends CoreInstruction{

	public IfElse(InstructionData instructionData, InstructionNode root) {
		super(instructionData, root);
	}
	
	public double execute(){
		if(getArgumentDouble(0) != 0){
			return runListCommands(1);
		}
		else{
			return runListCommands(2);
		}
	}

}
