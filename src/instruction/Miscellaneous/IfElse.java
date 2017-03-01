package instruction.Miscellaneous;

import java.util.List;

import instruction.InstructionData;

public class IfElse extends Miscellaneous{

	public IfElse(InstructionData instructionData,  List<String> args) {
		super(instructionData, args);
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
