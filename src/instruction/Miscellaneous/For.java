package instruction.Miscellaneous;

import java.util.List;

import instruction.InstructionData;
import user_structures.VariableData;

/**
 * Instruction for a for statement, takes two arguments: 
 * 1) The conditions [ variable start end inc ] 
 * 2) The commands [ commands ]
 * 
 * @author maddiebriere
 *
 */

public class For extends Miscellaneous {

	public For(InstructionData instructionData, List<String> args, String myText) {
		super(instructionData, args, myText);
	}

	@Override
	public double execute() {
		String[] args = getArgumentString(0).split(" ");
		VariableData counter = initializeVariable(args[0], Double.parseDouble(args[1]));
		return iterateThroughLoop(counter, Double.parseDouble(args[2]), Double.parseDouble(args[3]));
	}
}
