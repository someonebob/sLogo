package instruction.MultipleTurtleCommands;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import instruction.InstructionData;
import util.MathUtil;
import view.ActorView;

/**
 * <p>
 * <b>SLogo Documentation:</b> Concrete Instruction subclass which takes two
 * SLogo arguments: 1) list with single element, a condition 2) list of commands
 * Temporarily sets the subset of "told" turtles to match all existing turtles
 * who satisfy the condition in the first argument. (The condition is false if
 * it evaluates to 0 and true if it evaluates to a nonzero value). Then executes
 * the list of commands for each of these turtles. Returns the return value of
 * the last executed command (or 0 if none do), and resets the subset of "told"
 * turtles to match what it initially was before this command was executed.
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
public class AskWith extends MultipleTurtleCommand {

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
	public AskWith(InstructionData data, List<String> args, String myText) {
		super(data, args, myText);
	}

	/**
	 * Executes command as described in class Javadoc comment. Assumptions:
	 * Correct number of SLogo arguments, numerically valued SLogo arguments,
	 * second SLogo argument contains valid commands. Dependencies: Include
	 * Instruction Data and Instruction.
	 * 
	 * @return return value of last executed command, or 0 if none do
	 */
	@Override
	public double execute() {
		Set<Integer> savedToldActors = new TreeSet<>();
		for (ActorView actor : getInstructionData().getActorList()) {
			if (actor.isTold()) {
				savedToldActors.add(actor.getID().getID());
			}
		}

		List<Integer> idsAsInts = new ArrayList<>();
		for (ActorView actor : getInstructionData().getActorList()) {
			Collection<Integer> actorToCheck = new TreeSet<>();
			actorToCheck.add(actor.getID().getID());
			handleTolds(actorToCheck);
			double conditionValue = runListCommands(0);
			if (!MathUtil.doubleEquals(conditionValue, 0.0)) {
				idsAsInts.add(actor.getID().getID());
			}
		}
		handleTolds(idsAsInts);

		double returnValue = runListCommands(1);
		handleTolds(savedToldActors);
		return returnValue;
	}
}