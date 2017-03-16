package instruction.Miscellaneous;

import java.util.List;

import instruction.InstructionData;
import user_structures.VariableData;

/**
 * <p>
 * <b>SLogo Documentation:</b> SLogo command that takes two arguments, first the
 * number of iterations, and second a list of command to execute on each
 * iteration. Iterates through the listed commands, creating and incrementing
 * variable :repcount throughout. Returns the return value of the last command
 * to execute (or 0 if none do).
 * </p>
 * 
 * <p>
 * <b>Java Backend Documentation:</b> It is assumed that no arguments to the
 * constructor are null, and that args contains the correct number of non-null
 * entries for this Instruction subclass. Dependencies are InstructionData,
 * List, String, Instruction, VariableData, and the helpers in superclass
 * Miscellaneous.
 * </p>
 * 
 * @author maddiebriere
 * @author Matthew Barbano
 *
 */

public class Repeat extends Miscellaneous {
	private static final String COUNTER_NAME = "repcount";

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
	public Repeat(InstructionData instructionData, List<String> args, String myText) {
		super(instructionData, args, myText);
	}

	/**
	 * Executes command as described in class Javadoc comment. Assumptions:
	 * Correct number of SLogo arguments, numerically valued SLogo arguments.
	 * Dependencies: Instruction, VariableData, and the helpers in superclass
	 * Miscellaneous.
	 * 
	 * @return the return value of the final command executed (or 0 if none do)
	 */
	@Override
	public double execute() {
		VariableData repcount = initializeVariable(COUNTER_NAME, 1.0);
		return iterateThroughLoop(repcount, getArgumentDouble(0), 1.0);
	}
}
