package instruction.MathOperations;

import java.util.List;

import exceptions.MathException;
import instruction.InstructionData;
import util.MathUtil;

/**
 * <p>
 * <b>SLogo Documentation:</b> Concrete Instruction subclass which returns the
 * tangent of its single SLogo argument, given in degrees. Throws a
 * MathException if it occurs on the tangent function's asymptote.
 * </p>
 * 
 * <p>
 * <b>Java Backend Documentation:</b> It is assumed that no arguments to the
 * constructor are null, and that args contains the correct number of non-null
 * entries for this Instruction subclass. Dependencies are InstructionData,
 * List, String, Math's toRadians() and tan(), MathUtil's hasIntegerValue(),
 * Instruction's getArgumentDouble(), and MathException.
 * </p>
 * 
 * @author Matthew Barbano
 *
 */
public class Tangent extends MathOperation {
	private static final String RESOURCE_TANGENT_NAME = "TangentMessage";

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
	public Tangent(InstructionData data, List<String> args, String myText) {
		super(data, args, myText);
	}

	/**
	 * Executes command as described in class Javadoc comment. Uses
	 * java.lang.Math's tan() and toRadians(). Assumptions: Correct number of
	 * SLogo arguments, numerically valued SLogo arguments, angle does not fall
	 * on an asymptote. Dependencies: Math's toRadians() and tan(),
	 * Instruction's getArgumentDouble().
	 * 
	 * @throws MathException
	 *             if SLogo argument = 90 + 180n, for some integer n
	 * @return the tangent of the angle
	 */
	@Override
	public double execute() {
		double angleInRadians = Math.toRadians(getArgumentDouble(0));
		if (MathUtil.hasIntegerValue((angleInRadians / Math.PI) - 0.5)) {
			throw new MathException(RESOURCE_TANGENT_NAME);
		}
		return Math.tan(angleInRadians);
	}
}
