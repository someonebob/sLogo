package util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import instruction.Instruction;
import interpreter.InstructionClassifier;
import interpreter.InstructionNode;

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

}
