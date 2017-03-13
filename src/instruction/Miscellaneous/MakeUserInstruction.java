package instruction.Miscellaneous;

import java.util.List;

import instruction.InstructionData;
import interpreter.clean.InstructionSplitter;
import user_structures.FunctionData;

/**
 * 
 * Instruction for a user-defined instruction.
 * This class stores a String name that represents 
 * the command (e.g., dance, flower) -- this name is given
 * by the user. Also has a String that can be parsed
 * upon execution of this command to perform the intended functionality.
 * 
 * NOTE: The actual command is not executed, nor even parsed when
 * the command is created. Any problems with parsing 
 * created by the body of the function will occur when
 * you try to run the command, not when it is defined.
 * 
 * @author maddiebriere
 *
 */

public class MakeUserInstruction extends Miscellaneous {

	public MakeUserInstruction(InstructionData instructionData, List<String> args, String myText) {
		super(instructionData, args, myText);
	}

	@Override
	public double execute() {
		List <String> args = InstructionSplitter.getInstructionStrings(getArgumentString(1));
		for(int i=0; i< args.size(); i++){
			args.set(i, args.get(i).substring(1));
		}
		FunctionData function = new FunctionData(getArgumentString(0), getArgumentString(2), args);
		getInstructionData().addFunction(function);

		return 0;
	}

}
