package util;

/**
 * Class dedicated to reading in the number of arguments held by each command
 * type
 * 
 * @author maddiebriere
 *
 */

public class ArgumentReader
{
	public static String NUM_ARGS = "resources/interpreter/NumArgs";

	public static int getNumArgs(String instructionType)
	{
		// TODO: Error handling
		int numArgs = ResourceToList.getNumericalTerm(NUM_ARGS, instructionType);
		return numArgs;
	}

}
