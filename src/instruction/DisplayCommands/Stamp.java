package instruction.DisplayCommands;

import java.util.List;

import instruction.ActorSpecificInstruction;
import instruction.InstructionData;

/**
 * Places a "stamp" image of the turtle at its current position
 * with its current heading.
 * @author Matthew Barbano
 *
 */
public class Stamp extends DisplayCommand implements ActorSpecificInstruction {
	
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
	public Stamp(InstructionData instructionData, List<String> args, String myText) {
		super(instructionData, args, myText);
	}
	
	/**
	 * Draws the stamp and returns the image index.
	 */
	@Override
	protected double execute() {
		getInstructionData().drawStamp();
		return getActiveImageIndex();
	}

}
