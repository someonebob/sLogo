package interpreter;

import java.util.List;

import instruction.*;
import util.InstructionSplitter;

/**
 * This interface will use a variety of resource files to transform An input
 * string into a useable command (we call it an Instruction). By returning an
 * Instruction, we have transformed something with no functionality Into
 * something capable of executing and returning knowledge about itself.
 * 
 * @maddiebriere
 **/

public class Interpreter {

	private InstructionClassifier myClassifier;

	public Interpreter(String language) {
		myClassifier = new InstructionClassifier(language);
	}

	/**
	 * Parse the instruction (Defined by s) and give Instructions access to
	 * information about current state through InstructionData. Once parsed,
	 * execute the created Instruction.
	 * 
	 * @param instruction
	 *            Input line from user
	 * @param info
	 *            InstructionData object representing current information
	 */
	public void parseAndRun(String instruction, InstructionData info) {
		/**
		 * TODO: Decide desired location of responsibilities.. should we put
		 * more control of execution in Instruction, Interpreter,
		 * InstructionNode, etc.?
		 */
		InstructionNode head = parse(instruction);
		Instruction toExecute = myClassifier.generateInstruction(head.getMyValue());
		toExecute.execute();
	}

	/**
	 * Takes a String and converts it into a tree, with an InstructionNode at
	 * the root node, with any number of children (also InstructionNodes).
	 * 
	 * This method only splits the given input into a list of instructions and
	 * then calls upon helper methods to create the root node from the list
	 * 
	 * @param toParse
	 *            The String command passed in by the front-end
	 * @return Root node of the instruction, read from toParse
	 */
	private InstructionNode parse(String toParse) {
		List<Instruction> splitCommands = InstructionSplitter.getInstructions(toParse, getMyClassifier());
		InstructionNode head = TreeBuilder.buildTree(splitCommands); // generate
																		// from
																		// root
		return head;
	}

	public InstructionClassifier getMyClassifier() {
		return myClassifier;
	}

	public void setMyClassifier(InstructionClassifier myClassifier) {
		this.myClassifier = myClassifier;
	}

}
