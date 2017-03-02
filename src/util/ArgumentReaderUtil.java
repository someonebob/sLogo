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

	public static int getNumArgs(String instructionType, InstructionData data){
		// TODO: Error handling
		if(data.containsFunction(instructionType) != null){ //user-defined function
			return data.containsFunction(instructionType).getArgs().size();
		}
		int numArgs = ResourceToListUtil.getNumericalTerm(NUM_ARGS, instructionType);
		return numArgs;
	}

}
