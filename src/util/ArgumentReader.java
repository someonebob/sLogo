package util;

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
	
	private String numArgsFile;
	private List<Entry<String, Pattern>> myNumArgsList;
	
	public ArgumentReader(){
		numArgsFile = NUM_ARGS;
	}
	
	private void initializeList(){
		
	}
}
