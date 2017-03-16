package instruction.MultipleTurtleCommands;

import java.util.List;

import instruction.InstructionData;

/**
 * <p>
 * <b>SLogo Documentation:</b> Concrete Instruction subclass which takes one
 * argument, a List of turtle ids (assumed existing or consecutively increasing
 * integers starting with one past the last existing turtle). Sets all these
 * turtles to "told" (and creates any nonexistent turtles in the process).
 * Returns the last id value in the argument list, or 0 if the list is empty.
 * </p>
 * 
 * <p>
 * <b>Java Backend Documentation:</b> It is assumed that no arguments to the
 * constructor are null, and that args contains the correct number of non-null
 * entries for this Instruction subclass. Dependencies include InstructionData,
 * List, and String.
 * </p>
 * 
 * @author Matthew Barbano
 *
 */
public class Tell extends MultipleTurtleCommand {

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
	public Tell(InstructionData data, List<String> args, String myText) {
		super(data, args, myText);
	}

	/**
	 * Executes command as described in class Javadoc comment. Assumptions:
	 * Correct number of SLogo arguments, numerically valued SLogo arguments,
	 * SLogo list argument contains ids that are existing or consecutively
	 * increasing integers starting with one past the last existing turtle.
	 * Dependencies: Include Instruction Data and Instruction.
	 * 
	 * @return return id of currently active actor
	 */
	@Override
	public double execute() {
		List<Integer> idsAsInts = convertStringToIntegerList(getArgumentString(0));
		handleTolds(idsAsInts);
		return idsAsInts.get(idsAsInts.size() - 1);
	}
}