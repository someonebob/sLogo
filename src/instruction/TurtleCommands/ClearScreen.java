package instruction.TurtleCommands;

import java.util.List;

import instruction.InstructionData;
import javafx.geometry.Point2D;
import util.MathUtil;
import view.TurtleView;

/**
 * <p>
 * <b>SLogo Documentation:</b> Concrete Instruction subclass which sends the
 * active actor to the home position and erases its trails. Returns the distance
 * traveled to reach home.
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
public class ClearScreen extends TurtleCommand {

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
	public ClearScreen(InstructionData instructionData, List<String> args, String myText) {
		super(instructionData, args, myText);
	}

	/**
	 * Executes command as described in class Javadoc comment. Assumptions:
	 * Correct number of SLogo arguments, numerically valued SLogo arguments.
	 * Dependencies: Instruction, TurtleCommand, MathUtil.
	 * 
	 * @return distance traveled
	 */
	@Override
	public double execute() {
		double distanceTraveled = MathUtil.distance(getPosition().getX(), getPosition().getY());
		if (this.getActiveActor() instanceof TurtleView) {
			// TODO: penup doesn't actually put the pen up
			this.getActiveActor().move(new Point2D(0, 0));
			setPosition(0, 0);
			this.getActiveActor().getPen().clear();
			this.getActiveActor().getPen().penDown();
		}
		return distanceTraveled;
	}
}
