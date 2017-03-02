package instruction.Miscellaneous;

import java.util.List;

import instruction.InstructionData;

/**
 * Class to construct a variable, adding its name
 * to the workspace and changing/setting its value
 * 
 * @author maddiebriere
 *
 */

public class MakeVariable extends Miscellaneous {
	
	public MakeVariable(InstructionData instructionData,  List<String> args, String myText) {
		super(instructionData, args, myText);
	}

	@Override
	public double execute(){
		return initializeVariable(getArgumentString(0).substring(1), 
				getArgumentDouble(1)).getValue();
	}

	

}
