package interpreter.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import instruction.InstructionData;
import util.Pair;

/**
 * Used to read in the type of grouping 
 * used for a command:
 * 1) Layer
 * 2) Multiple
 * 
 * @author maddiebriere
 *
 */

public class GroupReaderUtil {

	public static final String GROUPS = "resources/interpreter/Groupings";
	public static final String DEFAULT = "Multiple";
	
	public static Pair<String, Integer> getGroupAndNumArgs(String instructionType, String exactCommand, InstructionData data){
		String group = getGroup(instructionType);
		int numArgs = ArgumentReaderUtil.getNumArgs(instructionType, exactCommand, data);
		return new Pair<String, Integer>(group, numArgs);
	}
	
	public static String getGroup(String classification){
		List<Entry<String, Pattern>> toCheck = new ArrayList<Entry<String,Pattern>>();
		ResourceToListUtil.addTerms(GROUPS, toCheck); 
		return match(classification, toCheck);
	}
	
    private static String match(String text, List<Entry<String, Pattern>> toCheck) {
        /**
         * Default to higher classifiers if only possibility
         */
        for (Entry<String, Pattern> e : toCheck) {
            if (match(text, e.getKey())) {
            	return e.getValue().toString();
            }
        }
        return DEFAULT;
    }
    
    /**
     * Check if a String matches a pattern
     * @author rcd
     * @param text String to check
     * @param regex Pattern to compare against
     * @return true if text and regex match, false otherwise
     */
    private static boolean match (String text, String regex) {
        return text.equals(regex);
    }
}
