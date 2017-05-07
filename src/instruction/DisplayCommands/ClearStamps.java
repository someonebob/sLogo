package instruction.DisplayCommands;

import java.util.List;

import instruction.ActorSpecificInstruction;
import instruction.InstructionData;

/**
 * Removes all drawn stamps from the screen.
 * @author Matthew Barbano
 *
 */
public class ClearStamps extends DisplayCommand implements ActorSpecificInstruction {

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
	public ClearStamps(InstructionData instructionData, List<String> args, String myText) {
		super(instructionData, args, myText);
	}
	
	/**
	 * Removes the stamps. Returns 1 if there were any stamps to remove, 0 if
	 * there were none.
	 */
	@Override
	protected double execute() {
		int returnValue = getInstructionData().getStamps().isEmpty() ? 0 : 1;
		getInstructionData().clearStamps();
		return returnValue;
	}

}
