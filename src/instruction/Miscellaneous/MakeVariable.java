package instruction.Miscellaneous;

import java.util.List;

import instruction.InstructionData;

public class MakeVariable extends Miscellaneous {
	
	public MakeVariable(InstructionData instructionData,  List<String> args, String myText) {
		super(instructionData, args, myText);
	}

	@Override
	public double execute(){
		return initializeVariable(getArgumentString(0), getArgumentDouble(1)).getValue();
	}

	

}
