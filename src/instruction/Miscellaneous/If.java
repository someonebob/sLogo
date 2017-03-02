package instruction.Miscellaneous;

import java.util.List;

import instruction.InstructionData;
/**
 * Instruction for if statement
 * 
 * @author maddiebriere
 *
 */

public class If extends Miscellaneous {
	
	public If(InstructionData instructionData,  List<String> args, String myText) {
		super(instructionData, args, myText);
	}

	@Override
	public double execute(){
		if(getArgumentDouble(0) != 0){
			return runListCommands(1);
		}
		return 0;
	}

	

}
