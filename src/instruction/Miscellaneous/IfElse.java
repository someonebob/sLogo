package instruction.Miscellaneous;

import java.util.List;

import instruction.InstructionData;
/**
 * Instruction for if-else statement. Very similar to
 * the If class, but includes another argument which
 * is executed when the condition is NOT true. The
 * arguments are: 
 * 
 * 1) The conditional (e.g., 1, 0) which dictates whether
 * or not to execute the body of the statement
 * 2) (if) The conditional result (e.g, [ fd 50 ]) to be executed
 * if the condition is equivalent to 1
 * 3) (else) The conditional result (e.g, [ fd 50 ]) to be executed
 * if the condition is equivalent to 0
 * 
 * @author maddiebriere
 *
 */

public class IfElse extends Miscellaneous {

	public IfElse(InstructionData instructionData, List<String> args, String myText) {
		super(instructionData, args, myText);
	}

	public double execute() {
		if (getArgumentDouble(0) != 0) {
			return runListCommands(1);
		} else {
			return runListCommands(2);
		}
	}

}
