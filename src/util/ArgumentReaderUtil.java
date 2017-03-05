package util;

import instruction.InstructionData;

/**
 * Class dedicated to reading in the number of arguments held by each command
 * type
 * 
 * @author maddiebriere
 *
 */

public final class ArgumentReaderUtil
{
	private ArgumentReaderUtil(){}
	public static String NUM_ARGS = "resources/interpreter/NumArgs";

	/**
	 * First looks for the number of arguments listed under the general instructionType. If
	 * it finds nothing, then it tries to find the arguments specific to the name using information
	 * in the instruction data (e.g., a user-defined function called dance may return 2 as its argument 
	 * number if the InstructionData holds information about dance, including the number of arguments
	 * as 2).
	 * 
	 * @param instructionType The general type of the instruction (E.g., ListStart, Comment)
	 * @param exactCommand The exact text (e.g., [, dance, to)
	 * @param data InstructionData object
	 * @return integer number of arguments
	 */
	public static int getNumArgs(String instructionType, String exactCommand, InstructionData data){
		// TODO: Error handling
		if(data.containsFunction(exactCommand) != null){ //user-defined function
			return data.containsFunction(exactCommand).getArgs().size();
		}
		int numArgs = ResourceToListUtil.getNumericalTerm(NUM_ARGS, instructionType);
		return numArgs;
	}

}
