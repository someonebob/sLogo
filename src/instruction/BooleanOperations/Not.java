package instruction.BooleanOperations;

import java.util.List;

import instruction.InstructionData;
import util.MathUtil;

/**
 * <p>
 * <b>SLogo Documentation:</b> Concrete Instruction subclass which performs
 * logical NOT on the single SLogo input parameter. As consistent with SLogo
 * convention, 0 is treated as false and any nonzero value is treated as true.
 * Returns 1 if so and 0 if not.
 * </p>
 * 
 * <p>
 * <b>Java Backend Documentation:</b> It is assumed that no arguments to the
 * constructor are null, and that args contains the correct number of non-null
 * entries for this Instruction subclass. Dependencies are InstructionData,
 * List, String, MathUtil's doubleLessThan, and Instruction's
 * getArgumentDouble().
 * </p>
 * 
 * @author Matthew Barbano
 *
 */
public class Not extends BooleanOperation {

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
	public Not(InstructionData data, List<String> args, String myText) {
		super(data, args, myText);
	}

	/**
	 * Executes command as described in class Javadoc comment. Uses MathUtil's
	 * doubleEquals() for roundoff-safe equality checking and ternary statement
	 * for brevity. Assumptions: Correct number of SLogo arguments, numerically
	 * valued SLogo arguments. Dependencies: MathUtil's doubleEquals(),
	 * Instruction's getArgumentDouble().
	 * 
	 * @return 1 for true, 0 for false
	 */
	@Override
	public double execute() {
		return MathUtil.doubleEquals(getArgumentDouble(0), 0) ? 1 : 0;
	}
}
