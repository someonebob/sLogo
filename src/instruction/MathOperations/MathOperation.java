package instruction.MathOperations;

import java.util.List;

import instruction.Instruction;
import instruction.InstructionData;

/**
 * Superclass of all math operation Instructions. All of these Instructions
 * perform a computation and return the result. Any computation that raises an exception
 * related to performing this math throws a MathException. It is assumed that instructionData and
 * args are not null, and that args contains the correct number of non-null
 * entries for each Instruction subclass. Dependencies are InstructionData,
 * List, String, and Instruction's 3-argument constructor.
 * 
 * @author Matthew Barbano
 *
 */
public abstract class MathOperation extends Instruction {
	
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
	public MathOperation(InstructionData data,  List<String> args, String myText) {
		super(data, args, myText);
	}
}
