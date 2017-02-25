package interpreter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Purely a utility class, used for purposes of splitting an input line and
 * returning strings or Instructions from input
 * 
 * Hopeful extension: Cleansing of input
 * 
 * @author maddiebriere
 *
 */

public class InstructionSplitter {

	/**
	 * Parses string into individual words, uses the given instruction
	 * classifier to create corresponding InstructionNodes in list for return.
	 * NOTE: THESE INSTRUCTION NODES WILL NOT HAVE ANY CHILDREN -- THIS MUST
	 * BE COMPLETED BY TREE BUILDER.
	 * 
	 * @param toParse
	 *            Input line to be parsed for instructions
	 * @param classifier
	 *            InstructionClassifier with knowledge of syntax/language and
	 *            capabilities to generate an Instruction given a String name
	 *            (reflection)
	 * @return List of InstructionNodes corresponding to the instructions input
	 */
	public static List<InstructionNode> getInstructions(String toParse, InstructionClassifier classifier) {
		ArrayList<InstructionNode> toRet = new ArrayList<InstructionNode>();
		List<String> commands = getInstructionStrings(toParse);
		for (String name : commands) {
			String type = classifier.findShortcutKey(name);
			toRet.add(new InstructionNode(type,name));
		}
		return toRet;
	}

	public static List<String> getInstructionStrings(String toParse) {
		// TODO: Error check for empty string
		return splitString(toParse);
	}

	/**
	 * Split String by whitespace to get relevant words
	 * 
	 * @param toParse
	 * @return
	 */
	private static List<String> splitString(String toParse) {
		ArrayList<String> toRet = new ArrayList<String>();
		Scanner scan = new Scanner(toParse);
		while (scan.hasNext()) {
			toRet.add(scan.next());
		}
		scan.close();
		return toRet;
	}
	
	
	/**
	 * Removes the first instruction from the String
	 * @param toParse String to remove item from
	 * @return String with item removed
	 */
	public static String removeFirstItem(String toParse){
		String toRet="";
		List<String> parsed = getInstructionStrings(toParse);
		if(parsed.size()<=1){
			return toRet;
		}
		for(int i=1; i<parsed.size()-1; i++){
			toRet+=parsed.get(i) + " ";
		}
		toRet+=parsed.get(parsed.size()-1);
		return toRet;
	}

}
