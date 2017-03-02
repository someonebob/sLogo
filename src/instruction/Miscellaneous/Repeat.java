package instruction.Miscellaneous;

import java.util.List;

import instruction.InstructionData;
import user_structures.Variable;

public class Repeat extends Miscellaneous {
	private static final String COUNTER_NAME = "repcount";
	
	public Repeat(InstructionData instructionData,  List<String> args, String myText) {
		super(instructionData, args, myText);
	}

	@Override
	public double execute(){
		Variable repcount = initializeVariable(COUNTER_NAME, 1.0);
		return iterateThroughLoop(repcount, getArgumentDouble(0), 1.0);
	}
}

