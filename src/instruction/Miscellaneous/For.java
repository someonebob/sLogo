package instruction.Miscellaneous;

import java.util.List;

import instruction.InstructionData;
import user_structures.Variable;

/**
 * Instruction for a for statement
 * 
 * @author maddiebriere
 *
 */

public class For extends Miscellaneous {
	
	public For(InstructionData instructionData, List<String> args, String myText) {
		super(instructionData, args, myText);
	}

	//Assumption made that limit is constant
	//TODO: Error throwing
	@Override
	public double execute() {
		String[] args = getArgumentString(0).split(" ");
		Variable counter = new Variable(args[0], Integer.parseInt(args[1]));
		getInstructionData().addVariable(counter);
		return iterateThroughLoop(counter, Double.parseDouble(args[2]), Double.parseDouble(args[3]));
	}
}
