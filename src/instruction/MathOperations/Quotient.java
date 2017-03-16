package instruction.MathOperations;

import java.util.List;

import exceptions.MathException;
import instruction.InstructionData;
import util.MathUtil;

/**
 * <p>
 * <b>SLogo Documentation:</b> Concrete Instruction subclass returns its first
 * SLogo argument divided by its second SLogo argument. Throws a MathException
 * if its second is 0.
 * </p>
 * 
 * <p>
 * <b>Java Backend Documentation:</b> It is assumed that no arguments to the
 * constructor are null, and that args contains the correct number of non-null
 * entries for this Instruction subclass. Dependencies are InstructionData,
 * List, String, MathUtil's doubleEquals, Instruction's getArgumentDouble(), and
 * MathException.
 * </p>
 * 
 * @author Matthew Barbano
 *
 */
public class Quotient extends MathOperation {
	private static final String RESOURCE_QUOTIENT_NAME = "QuotientMessage";

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
	public Quotient(InstructionData data, List<String> args, String myText) {
		super(data, args, myText);
	}

	/**
	 * Executes command as described in class Javadoc comment. Assumptions:
	 * Correct number of SLogo arguments, numerically valued SLogo arguments,
	 * second SLogo argument is nonzero. Dependencies: MathUtil's
	 * doubleEquals(), Instruction's getArgumentDouble().
	 * 
	 * @throws MathException
	 *             2nd SLogo argument == 0
	 * @return the quotient
	 */
	@Override
	public double execute() {
		if (MathUtil.doubleEquals(getArgumentsDouble().get(1), 0.0)) {
			throw new MathException(RESOURCE_QUOTIENT_NAME);
		}
		return getArgumentDouble(0) / getArgumentDouble(1);
	}
}
