package instruction.MathOperations;

import java.util.List;

import exceptions.MathException;
import instruction.InstructionData;
import util.MathUtil;

/**
 * <p>
 * <b>SLogo Documentation:</b> Concrete Instruction subclass which returns the
 * first SLogo argument raised to the power of the second SLogo argument. Throws
 * a MathException if this operation would return an imaginary value.
 * </p>
 * 
 * <p>
 * <b>Java Backend Documentation:</b> It is assumed that no arguments to the
 * constructor are null, and that args contains the correct number of non-null
 * entries for this Instruction subclass. Dependencies are InstructionData,
 * List, String, MathUtil's doubleEquals, and Instruction's getArgumentDouble().
 * </p>
 * 
 * @author Matthew Barbano
 *
 */
public class Power extends MathOperation {
	private static final String RESOURCE_POWER_NAME = "PowerMessage";

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
	public Power(InstructionData data, List<String> args, String myText) {
		super(data, args, myText);
	}

	/**
	 * Executes command as described in class Javadoc comment. Uses
	 * java.lang.Math's pow(). Assumptions: Correct number of SLogo arguments,
	 * numerically valued SLogo arguments, does not return an imaginary value.
	 * Dependencies: Math's pow(), MathUtil's doubleLessThan() and
	 * hasIntegerValue(), Instruction's getArgumentDouble().
	 * 
	 * @throws MathException
	 *             if 1st SLogo argument < 0 and 2nd SLogo argument is not an
	 *             integer
	 * @return the result of this exponentiation
	 */
	@Override
	public double execute() {
		if (MathUtil.doubleLessThan(getArgumentDouble(0), 0.0) && !MathUtil.hasIntegerValue(getArgumentDouble(1))) {
			throw new MathException(RESOURCE_POWER_NAME);
		}
		return Math.pow(getArgumentDouble(0), getArgumentDouble(1));
	}

}
