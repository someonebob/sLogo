package instruction.Miscellaneous;

import java.util.List;

import instruction.InstructionData;
import user_structures.VariableData;

/**
 * <p>
 * <b>SLogo Documentation:</b> Instruction for a for statement, takes two
 * arguments: 1) The conditions [ variable start end inc ] 2) The commands [
 * commands ] Iterates over and executes the commands, changing variable from
 * start to end by inc increments. Returns the return value of the last executed
 * command (or 0 if none do).
 * </p>
 * 
 * <p>
 * <b>Java Backend Documentation:</b> It is assumed that no arguments to the
 * constructor are null, and that args contains the correct number of non-null
 * entries for this Instruction subclass. Dependencies are InstructionData,
 * List, String, Instruction, VariableData, Double, and the helpers in
 * superclass Miscellaneous.
 * </p>
 * 
 * @author maddiebriere
 * @author Matthew Barbano
 *
 */

public class For extends Miscellaneous {

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
	public For(InstructionData instructionData, List<String> args, String myText) {
		super(instructionData, args, myText);
	}

	/**
	 * Executes command as described in class Javadoc comment. Assumptions:
	 * Correct number of SLogo arguments, numerically valued SLogo arguments.
	 * Dependencies: String, Instruction, VariableData, Double, and the helpers
	 * in superclass Miscellaneous.
	 * 
	 * @return the return value of the final command executed (or 0 if none do)
	 */
	@Override
	public double execute() {
		String[] args = getArgumentString(0).split(" ");
		VariableData counter = initializeVariable(args[0], Double.parseDouble(args[1]));
		return iterateThroughLoop(counter, Double.parseDouble(args[2]), Double.parseDouble(args[3]));
	}
}
