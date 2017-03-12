package interpreter.clean;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import instruction.InstructionData;
import interpreter.classification.InstructionClassifier;
import interpreter.misc.InstructionNode;
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
	private final static String ERROR = "NO MATCH";
	
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
	public static List<InstructionNode> getInstructions(String toParse, InstructionClassifier classifier, InstructionData data) {
		ArrayList<InstructionNode> toRet = new ArrayList<InstructionNode>();
		List<String> commands = getInstructionStrings(toParse);
		for (int i=0; i<commands.size(); i++) {
			String name = commands.get(i);
			InstructionCleaner cleanse = new InstructionCleaner(data, classifier);
			if(!classifier.isValid(name, data)){
				name = cleanse.singleWordClean(name);
				commands.set(i, name);
			}
			String type = classifier.getInstructionType(name, data);
			toRet.add(new InstructionNode(type,name));
		}
		return toRet;
	}
	public static List<String> getInstructionStrings(String toParse) {
		return splitString(toParse);
	}
	
	/**
	 * Rewrite the Instruction from the given list of nodes
	 * @param list List of nodes
	 * @return String representing original instruction
	 */
	public static String rewriteNonLinkedInstruction(List<InstructionNode> list){
		String toRet = "";
		for(InstructionNode n: list){
			toRet += n.getMyCommand() + " ";
		}
		return toRet.substring(0,toRet.length()-1);
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