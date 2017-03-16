package instruction.Miscellaneous;

import java.util.List;

import instruction.InstructionData;

/**
 * <p>
 * <b>SLogo Documentation:</b> * Class to construct a variable, adding its name
 * to the workspace and changing/setting its value.
 * 
 * DIFFERENT from the Variable class in that this produces the variable in the
 * first place -- it does not return the value of the variable as a run value
 * like the Variable class does.
 * 
 * Returns the value that the variable is set to.
 * </p>
 * 
 * <p>
 * <b>Java Backend Documentation:</b> It is assumed that no arguments to the
 * constructor are null, and that args contains the correct number of non-null
 * entries for this Instruction subclass. Dependencies are InstructionData,
 * List, String, Instruction, and VariableData.
 * </p>
 * 
 * @author maddiebriere
 * @author Matthew Barbano
 *
 */

public class MakeVariable extends Miscellaneous {

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
	public MakeVariable(InstructionData instructionData, List<String> args, String myText) {
		super(instructionData, args, myText);
	}

	/**
	 * Executes command as described in class Javadoc comment. Assumptions:
	 * Correct number of SLogo arguments, numerically valued SLogo arguments.
	 * Dependencies: Instruction, and the helpers in superclass Miscellaneous.
	 * 
	 * @return the value the variable is set to
	 */
	@Override
	public double execute() {
		return initializeVariable(getArgumentString(0).substring(1), getArgumentDouble(1)).getValue();
	}

}
