package instruction.Miscellaneous;

import java.util.List;

import instruction.InstructionData;

/**
 * <p>
 * <b>SLogo Documentation:</b> Instruction for if statement, takes two
 * arguments: 1) The conditional (e.g., 1, 0) which dictates whether or not to
 * execute the body of the statement. 0 represents false, and any nonzero value
 * represents true. 2) The conditional result (e.g, [ fd 50 ]) to be executed if
 * the condition is nonzero Returns the value of the last command to execute (or
 * 0 if none do).
 * </p>
 * 
 * <p>
 * <b>Java Backend Documentation:</b> It is assumed that no arguments to the
 * constructor are null, and that args contains the correct number of non-null
 * entries for this Instruction subclass. Dependencies are InstructionData,
 * List, String, Instruction, and the helpers in superclass Miscellaneous.
 * </p>
 * 
 * @author maddiebriere
 * @author Matthew Barbano
 *
 */

public class If extends Miscellaneous {

	/**
	 * Standard 3-argument constructor for the Instruction hierarchy. Through a
	 * series of super() constructor calls up the hierarchy, sets 3
	 * corresponding variables in Instruction. No assumptions cause direct
	 * impact in this constructor. Setting any arguments or entries in args to
	 * null will cause errors elsewhere. Design decision: Making args a List
	 * accommodates SLogo commands with different numbers of arguments.
	 * 
	 * @param instructionData
	 *            for accessing frontend data
	 * @param args
	 *            for storing arguments to SLogo commands
	 * @param myText
	 *            a String representation of this Instruction
	 */
	public If(InstructionData instructionData, List<String> args, String myText) {
		super(instructionData, args, myText);
	}

	/**
	 * Executes command as described in class Javadoc comment. Assumptions:
	 * Correct number of SLogo arguments, numerically valued SLogo arguments.
	 * Dependencies: Instruction, and the helpers in superclass Miscellaneous.
	 * 
	 * @return the return value of the final command executed (or 0 if none do)
	 */
	@Override
	public double execute() {
		if (getArgumentDouble(0) != 0) {
			return runListCommands(1);
		}
		return 0;
	}

}
