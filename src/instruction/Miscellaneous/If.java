package instruction.Miscellaneous;

import java.util.List;

import instruction.InstructionData;

/**
 * Instruction for if statement, takes two arguments:
 * 
 * 1) The conditional (e.g., 1, 0) which dictates whether or not to execute the
 * body of the statement 
 * 2) The conditional result (e.g, [ fd 50 ]) to be
 * executed if the condition is equivalent to 1
 * 
 * @author maddiebriere
 *
 */

public class If extends Miscellaneous {

	public If(InstructionData instructionData, List<String> args, String myText) {
		super(instructionData, args, myText);
	}

	@Override
	public double execute() {
		if (getArgumentDouble(0) != 0) {
			return runListCommands(1);
		}
		return 0;
	}

}
