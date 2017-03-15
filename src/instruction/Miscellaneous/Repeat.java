package instruction.Miscellaneous;

import java.util.List;

import instruction.InstructionData;
import user_structures.VariableData;

/**
 * Class to repeat a command the given
 * number of times.
 * 
 * @author Matthew Barbano
 *
 */

public class Repeat extends Miscellaneous {
	private static final String COUNTER_NAME = "repcount";
	
	public Repeat(InstructionData instructionData,  List<String> args, String myText) {
		super(instructionData, args, myText);
	}

	@Override
	public double execute(){
		VariableData repcount = initializeVariable(COUNTER_NAME, 1.0);
		return iterateThroughLoop(repcount, getArgumentDouble(0), 1.0);
	}
}

