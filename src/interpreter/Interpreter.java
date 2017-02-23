package interpreter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import instruction.*;

/**	This interface will use a variety of resource files to transform
	An input string into a useable command (we call it an Instruction).
	By returning an Instruction, we have transformed something with no functionality
	Into something capable of executing and returning knowledge about itself.
	
	@maddiebriere
**/

public class Interpreter{
	
	private InstructionClassifier myClassifier;
	
	public Interpreter(String language){
		myClassifier = new InstructionClassifier(language);
	}
	
	/**
	Parse the instruction from the input String and then execute the instruction
	 **/
	public void parseAndRun(String s, InstructionData i){
		InstructionNode head = parse(s);
		//Instruction toExecute = new Instruction(i, head);
		//toExecute.execute();
	}
	
	/**
	 * Takes a String and converts it into a tree, with an InstructionNode
	 * at the root node, with any number of children (also InstructionNodes).
	 * 
	 * @param toParse The String command passed in by the front-end
	 * @return Root node of the instruction, read from toParse
	 */
	private InstructionNode parse(String toParse){
		//TODO: Complete
		return null;
	}

	public InstructionClassifier getMyClassifier() {
		return myClassifier;
	}

	public void setMyClassifier(InstructionClassifier myClassifier) {
		this.myClassifier = myClassifier;
	}
	
}
