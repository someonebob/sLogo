package instruction.MathOperations;

import java.util.List;

import exceptions.MathException;
import instruction.InstructionData;
import util.MathUtil;

/**
 * <p>
 * <b>SLogo Documentation:</b> Concrete Instruction subclass which returns the
 * remainder of its first SLogo argument divided by its second. Throws a
 * MathException if either of its arguments are not integers or if the second
 * equals zero.
 * </p>
 * 
 * <p>
 * <b>Java Backend Documentation:</b> It is assumed that no arguments to the
 * constructor are null, and that args contains the correct number of non-null
 * entries for this Instruction subclass. Dependencies are InstructionData,
 * List, String, MathUtil's doubleEquals() and hasIntegerValue(), Instruction's
 * getArgumentDouble(), and MathException.
 * </p>
 * 
 * @author Matthew Barbano
 *
 */
public class Remainder extends MathOperation {
	private static final String RESOURCE_NOT_INTEGER_NAME = "RemainderNotIntegerMessage";
	private static final String RESOURCE_DIVISION_ZERO_NAME = "RemainderDivisionMessage";

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
	public Remainder(InstructionData data, List<String> args, String myText) {
		super(data, args, myText);
	}

	/**
	 * Executes command as described in class Javadoc comment. Assumptions:
	 * Correct number of SLogo arguments, numerically valued SLogo arguments,
	 * integer SLogo arguments, nonzero 2nd SLogo argument. Dependencies:
	 * MathUtil's hasIntegerValue() and doubleEquals(), Instruction's
	 * getArgumentDouble().
	 * 
	 * @return the remainder
	 */
	@Override
	public double execute() {
		if (!MathUtil.hasIntegerValue(getArgumentDouble(0)) || !MathUtil.hasIntegerValue(getArgumentDouble(1))) {
			throw new MathException(RESOURCE_NOT_INTEGER_NAME);
		}
		if (MathUtil.doubleEquals(getArgumentDouble(1), 0)) {
			throw new MathException(RESOURCE_DIVISION_ZERO_NAME);
		}
		return getArgumentDouble(0) % getArgumentDouble(1);
	}

}
