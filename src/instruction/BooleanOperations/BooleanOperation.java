package instruction.BooleanOperations;

import java.util.List;

import instruction.Instruction;
import instruction.InstructionData;

/**
 * Superclass of all boolean operation Instructions. Note that 0 is considered
 * false and any nonzero value is considered true as a convention in the SLogo
 * language. It is assumed that instructionData and args are not null, and that
 * args contains the correct number of non-null entries for each Instruction
 * subclass. Dependencies are InstructionData, List, String, and Instruction's
 * 3-argument constructor.
 * 
 * @author Matthew Barbano
 *
 */
public abstract class BooleanOperation extends Instruction {
	
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
	public BooleanOperation(InstructionData instructionData, List<String> args, String myText) {
		super(instructionData, args, myText);
	}
}
