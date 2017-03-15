package instruction.Miscellaneous;

import java.util.List;

import instruction.InstructionData;
import user_structures.VariableData;

/**
 * Class to perform a certain action a set number of times, iterating a given
 * variable from the value of 1 up to the given limit (For use in the commands).
 * 
 * Assumption: The limit passed to this function is a constant and not an
 * expression -- hopefully change with future adaptations.
 * 
 * @author maddiebriere
 *
 */
public class DoTimes extends Miscellaneous {

	public DoTimes(InstructionData instructionData, List<String> args, String myText) {
		super(instructionData, args, myText);
	}

	@Override
	public double execute() {
		String[] args = getArgumentString(0).split(" ");
		VariableData counter = initializeVariable(args[0], 1.0);
		return iterateThroughLoop(counter, Double.parseDouble(args[1]), 1.0);
	}

}
