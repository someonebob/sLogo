package instruction.DisplayCommands;

import java.util.List;

import instruction.Instruction;
import instruction.InstructionData;

/**
 * Superclass of all display command Instructions. All of these Instructions
 * access of change qualities about the GUI display of the turtle simulation,
 * such as pen color or the turtle image. It is assumed that instructionData and
 * args are not null, and that args contains the correct number of non-null
 * entries for each Instruction subclass. Dependencies are InstructionData,
 * List, String, and Instruction's 3-argument constructor.
 * 
 * @author Matthew Barbano
 *
 */
public abstract class DisplayCommand extends Instruction {

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
	public DisplayCommand(InstructionData instructionData, List<String> args, String myText) {
		super(instructionData, args, myText);
	}
	
}