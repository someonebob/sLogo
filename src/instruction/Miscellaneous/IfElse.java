package instruction.Miscellaneous;

import java.util.List;

import instruction.InstructionData;
/**
 * Instruction for if-else statement
 * 
 * @author maddiebriere
 *
 */

public class IfElse extends Miscellaneous{

	public IfElse(InstructionData instructionData,  List<String> args, String myText) {
		super(instructionData, args, myText);
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
