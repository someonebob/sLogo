package instruction.MathOperations;

import java.util.List;

import instruction.InstructionData;

/**
 * <p>
 * <b>SLogo Documentation:</b> Concrete Instruction subclass which takes the
 * arctangent of its single SLogo argument and returns a value in degress
 * between -180 and 180.
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
public class ArcTangent extends MathOperation {

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
	public ArcTangent(InstructionData data, List<String> args, String myText) {
		super(data, args, myText);
	}

	/**
	 * Executes command as described in class Javadoc comment. Uses
	 * java.lang.Math's toDegrees() and atan(). Assumptions: Correct number of
	 * SLogo arguments, numerically valued SLogo arguments. Dependencies: Math's
	 * toDegrees() and atan(), Instruction's getArgumentDouble().
	 * 
	 * @return result in degrees, in range -180 to 180
	 */
	@Override
	public double execute() {
		return Math.toDegrees(Math.atan(getArgumentDouble(0)));
	}

}
