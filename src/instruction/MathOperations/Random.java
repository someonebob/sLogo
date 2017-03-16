package instruction.MathOperations;

import java.util.List;

import exceptions.NonsensicalArgumentException;
import instruction.InstructionData;
import util.MathUtil;

/**
 * <p>
 * <b>SLogo Documentation:</b> Concrete Instruction subclass which returns a
 * random nonnegative number strictly less than its single SLogo argument.
 * Throws a MathException if its SLogo argument is negative.
 * </p>
 * 
 * <p>
 * <b>Java Backend Documentation:</b> It is assumed that no arguments to the
 * constructor are null, and that args contains the correct number of non-null
 * entries for this Instruction subclass. Dependencies are InstructionData,
 * List, String, Math's random(), MathUtil's doubleLessThan, and Instruction's
 * getArgumentDouble(), and MathException.
 * </p>
 * 
 * @author Matthew Barbano
 *
 */
public class Random extends MathOperation {
	private static final String RESOURCE_RANDOM_NAME = "RandomMessage";

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
	public Random(InstructionData data, List<String> args, String myText) {
		super(data, args, myText);
	}

	/**
	 * Executes command as described in class Javadoc comment. Uses
	 * java.lang.Math's random(). Assumptions: Correct number of SLogo
	 * arguments, numerically valued SLogo arguments, nonnegative second SLogo
	 * argument. Dependencies: Math's random(), MathUtil's doubleLessThan(),
	 * Instruction's getArgumentDouble().
	 * 
	 * @throws NonsensicalArgumentException
	 *             if SLogo argument < 0
	 * @return random number in range 0 <= number < SLogo argument,
	 */
	@Override
	public double execute() {
		if (MathUtil.doubleLessThan(getArgumentDouble(0), 0.0)) {
			throw new NonsensicalArgumentException(RESOURCE_RANDOM_NAME);
		}
		return Math.random() * getArgumentDouble(0);
	}

}
