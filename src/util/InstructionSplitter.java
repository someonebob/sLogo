package util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import instruction.Instruction;

/**
 * Purely a utility class, used for purposes of splitting an input line
 * and returning strings or Instructions from input
 * 
 * Hopeful extension: Cleansing of input
 * 
 * @author maddiebriere
 *
 */

public class InstructionSplitter {

	
	public static List<Instruction> getInstructions(String toParse){
		//TODO: Complete
		return null;
	}
	
	public static List<String> getInstructionStrings(String toParse){
		//TODO: Error check for empty string
		return splitString(toParse);
	}
	
	/**
	 * Split String by whitespace to get relevant words
	 * 
	 * @param toParse
	 * @return
	 */
	private static List<String> splitString(String toParse){
		ArrayList<String> toRet = new ArrayList<String>();
		Scanner scan = new Scanner(toParse);
		while(scan.hasNext()){
			toRet.add(scan.next());
		}
		return toRet;
	}

	
}
