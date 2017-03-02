package instruction.Miscellaneous;

import java.util.List;

import instruction.InstructionData;
import user_structures.Variable;

/**
 * Class to perform a certain action
 * from 1 - the given limit.
 * 
 * Assumption: The limit passed to this function is a constant
 * and not an expression -- hopefully change with future
 * adaptations.
 * 
 * @author maddiebriere
 *
 */
public class DoTimes extends Miscellaneous {
	
	public DoTimes(InstructionData instructionData, List<String> args, String myText) {
		super(instructionData, args, myText);
	}

	//Assumption made that limit is constant
	//TODO: Error throwing
	@Override
	public double execute() {
		String[] args = getArgumentString(0).split(" ");
		Variable counter = new Variable(args[0], 1.0);
		getInstructionData().addVariable(counter);
		return iterateThroughLoop(counter, Double.parseDouble(args[1]), 1.0);
	}

}
