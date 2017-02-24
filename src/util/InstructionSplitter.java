package util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import instruction.Instruction;
import interpreter.InstructionClassifier;

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
	 * classifier to create corresponding instructions in list for return.
	 * 
	 * @param toParse
	 *            Input line to be parsed for instructions
	 * @param classifier
	 *            InstructionClassifier with knowledge of syntax/language and
	 *            capabilities to generate an Instruction given a String name
	 *            (reflection)
	 * @return List of Instructions corresponding to the instructions input
	 */
	public static List<Instruction> getInstructions(String toParse, InstructionClassifier classifier) {
		ArrayList<Instruction> toRet = new ArrayList<Instruction>();
		List<String> commands = getInstructionStrings(toParse);
		for (String s : commands) {
			toRet.add(classifier.generateInstruction(s));
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
