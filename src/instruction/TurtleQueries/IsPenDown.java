package instruction.TurtleQueries;

import java.util.List;

import instruction.InstructionData;
import view.TurtleView;

/**
 * /**
 * <p>
 * <b>SLogo Documentation:</b> Returns 1 if active actor's pen is down, and 0
 * otherwise.
 * </p>
 * 
 * <p>
 * <b>Java Backend Documentation:</b> It is assumed that no arguments to the
 * constructor are null, and that args contains the correct number of non-null
 * entries for this Instruction subclass. Dependencies include InstructionData,
 * List, and String.
 * </p>
 * 
 * @author jimmy
 */

public class IsPenDown extends TurtleQuery {

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
	public IsPenDown(InstructionData instructionData, List<String> args, String myText) {
		super(instructionData, args, myText);
	}

	/**
	 * Executes command as described in class Javadoc comment. Assumptions:
	 * Correct number of SLogo arguments, numerically valued SLogo arguments.
	 * Dependencies: InstructionData, ActorView.
	 * 
	 * @return active actor's heading
	 */
	@Override
	public double execute() {
		if (getInstructionData().getActiveActor() instanceof TurtleView) {
			return ((TurtleView) getInstructionData().getActiveActor()).getPen().isUp() ? 0 : 1;
		}
		return 0;
	}

}
