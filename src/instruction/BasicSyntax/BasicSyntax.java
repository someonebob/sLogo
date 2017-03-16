package instruction.BasicSyntax;

import java.util.List;

import instruction.Instruction;
import instruction.InstructionData;

/**
 * The purpose of this class, which inherits all behavior from Instruction, is
 * to act as the superclass for all its subclasses that involve parsing
 * different expressions that the user could enter in the InputBox. It is
 * assumed that instructionData and args are not null, and that args contains
 * the correct number of non-null entries for each Instruction subclass. The
 * dependencies of BasicSyntax are InstructionData, List, String, Double, and
 * Instruction (particularly Instruction's 3-argument constructor and
 * getMyText()).
 * 
 * @author maddiebriere
 * @author Matthew Barbano
 */
public class BasicSyntax extends Instruction {

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
	public BasicSyntax(InstructionData instructionData, List<String> args, String myText) {
		super(instructionData, args, myText);
	}

	/**
	 * This method overrides and implements Instruction's execute() as returning
	 * the String representation of this Instruction as a double. It is assumed
	 * that Instruction's myText is not null and can be successfully parsed to a
	 * double value.
	 * 
	 * @return the value of getMyText() as a double
	 */
	@Override
	public double execute() {
		return Double.parseDouble(getMyText());
	}

}
