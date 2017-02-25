package util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.regex.Pattern;

/**
 * Class dedicated to reading in the number of arguments
 * held by each command type
 * 
 * @author maddiebriere
 *
 */

public class ArgumentReader {
	public static String NUM_ARGS = "resources/languages/NumArgs";
	
	public static int getNumArgs(String instructionType){
		int numArgs=1;
		List<Entry<String,Pattern> >myNumArgsList = new ArrayList<Entry<String,Pattern>>();
		ResourceToList.addTerms(NUM_ARGS, myNumArgsList);
		//TODO: Finish

		return numArgs;
	}
	
	
}
