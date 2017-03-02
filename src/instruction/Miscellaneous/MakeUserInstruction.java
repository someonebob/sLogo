package instruction.Miscellaneous;

import java.util.List;

import instruction.InstructionData;
import interpreter.InstructionSplitter;
import user_structures.FunctionData;

/**
 * 
 * Instruction for a user-defined instruction.
 * Stores a String name
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
		// TODO Auto-generated method stub
		List <String> args = InstructionSplitter.getInstructionStrings(getArgumentString(1));
		for(int i=0; i< args.size(); i++){
			args.set(i, args.get(i).substring(1));
		}
		FunctionData function = new FunctionData(getArgumentString(0), getArgumentString(2), args);
		getInstructionData().addFunction(function);

		
		return 0;//TODO: What to return?
	}

}
