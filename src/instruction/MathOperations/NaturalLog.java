package instruction.MathOperations;

import java.util.List;

import exceptions.MathException;
import instruction.InstructionData;
import util.MathUtil;

/**
 * <p>
 * <b>SLogo Documentation:</b> Concrete Instruction subclass which returns the
 * natural logarithm of its single SLogo argument. Throws a MathException if its
 * SLogo argument is nonpositive.
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
public class NaturalLog extends MathOperation {
	private static final String RESOURCE_LOG_NAME = "NaturalLogarithmMessage";

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
	public NaturalLog(InstructionData data, List<String> args, String myText) {
		super(data, args, myText);
	}

	/**
	 * Executes command as described in class Javadoc comment. Uses
	 * java.lang.Math's log(). Assumptions: Correct number of SLogo arguments,
	 * numerically valued SLogo arguments, SLogo argument is positive.
	 * Dependencies: Instruction's getArgumentDouble(), MathUtil's
	 * doubleLessThanEquals(), MathException.
	 * 
	 * @throws MathException
	 *             if SLogo argument <= 0
	 * @return the natural logarithm
	 */
	@Override
	public double execute() {
		if (MathUtil.doubleLessThanEquals(getArgumentDouble(0), 0.0)) {
			throw new MathException(RESOURCE_LOG_NAME);
		}
		return Math.log(getArgumentDouble(0));
	}

}
