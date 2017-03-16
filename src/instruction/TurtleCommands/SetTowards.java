package instruction.TurtleCommands;

import java.util.List;

import instruction.InstructionData;

/**
 * <p>
 * <b>SLogo Documentation:</b> Concrete Instruction subclass which modifies the
 * actor's heading so it faces towards the point (x, y), as specified by its
 * first and second SLogo arguments. Returns the change in heading.
 * </p>
 * 
 * <p>
 * <b>Java Backend Documentation:</b> It is assumed that no arguments to the
 * constructor are null, and that args contains the correct number of non-null
 * entries for this Instruction subclass. Dependencies are InstructionData,
 * List, String, MathUtil's doubleEquals, and Instruction's getArgumentDouble().
 * </p>
 * 
 * @author jimmy
 *
 */
public class SetTowards extends TurtleCommand {

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
	public SetTowards(InstructionData instructionData, List<String> args, String myText) {
		super(instructionData, args, myText);
	}

	/**
	 * Executes command as described in class Javadoc comment. Assumptions:
	 * Correct number of SLogo arguments, numerically valued SLogo arguments.
	 * Dependencies: Instruction, TurtleCommand, ActorView, Math.
	 * 
	 * @return degrees turned
	 */
	@Override
	public double execute() {
		double x = getArgumentDouble(0);
		double y = getArgumentDouble(1);
		double currX = this.getActiveActor().getLocation().getX();
		double currY = this.getActiveActor().getLocation().getY();
		double currHeading = this.getActiveActor().getHeading();
		double newHeading = Math.toDegrees(Math.atan((y - currY) / (x - currX)));
		if (y - currY < 0) {
			newHeading += 180;
		}
		this.setHeading(newHeading);
		return newHeading - currHeading;
	}
}
