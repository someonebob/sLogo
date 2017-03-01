package instruction.Miscellaneous;

import java.util.List;

import instruction.InstructionData;

public class If extends Miscellaneous {
	
	public If(InstructionData instructionData,  List<String> args) {
		super(instructionData, args);
	}

	@Override
	public double execute(){
		if(getArgumentDouble(0) != 0){
			return runListCommands(1);
		}
		return 0;
	}

	

}
