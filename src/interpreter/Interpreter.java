package interpreter;

import instruction.*;
import interpreter.builders.TreeBuilder;
import interpreter.classification.InstructionClassifier;
import interpreter.clean.InstructionCleaner;
import interpreter.execute.TreeExecuter;
import interpreter.misc.InstructionNode;
import util.Pair;

/**
 * This class uses a variety of resource files to transform an input string into
 * a useable command (we call it an Instruction). By returning an Instruction,
 * we have transformed something with no functionality Into something capable of
 * executing and returning knowledge about itself.
 * 
 * @maddiebriere
 **/
public class Interpreter {

	private InstructionData myData;
	private InstructionClassifier myClassifier;
	private String instruction;

	public Interpreter(InstructionData data) {
		myData = data;
		myClassifier = new InstructionClassifier(data.getLanguage());
		instruction="";
	}

	/**
	 * Parse the instruction (Defined by s) and give Instructions access to
	 * information about current state through InstructionData. Once parsed,
	 * execute the created Instruction.
	 * 
	 * Takes a String and converts it into a tree, with an InstructionNode at
	 * the root node, with any number of children (also InstructionNodes).
	 * 
	 * This method only splits the given input into a list of instructions and
	 * then calls upon helper methods to create the root node from the list.
	 * 
	 * Runs each InstructionNode as it is created.
	 * 
	 * @param instruction
	 *            Input line from user
	 */
	public double parseAndRun(String ins) {
		instruction = ins;
		double toRet = 0;
		InstructionCleaner clean = new InstructionCleaner(getMyData(), getMyClassifier());
		instruction = clean.fullTextClean(instruction);
		TreeBuilder builder = new TreeBuilder(instruction, getMyClassifier(), getMyData());
		while (!instruction.isEmpty()) {
			toRet = singleExecute(builder);
		}
		return toRet;
	}
	
	/**
	 * Executes a single loop through the current instructions, creating and executing
	 * the sub-tree for a single instruction.
	 * 
	 * @param builder TreeBuilder used to create each sub-tree
	 * @return the return value of execution
	 */
	private double singleExecute(TreeBuilder builder){
		Pair<InstructionNode, String> current = builder.buildTree();
		InstructionNode node = current.getMyA();
		instruction = current.getMyB();
		TreeExecuter executer = new TreeExecuter(getMyData(), getMyClassifier());
		return executer.execute(node);
	}

	public InstructionClassifier getMyClassifier() {
		return myClassifier;
	}

	public void setMyClassifier(InstructionClassifier myClassifier) {
		this.myClassifier = myClassifier;
	}

	public InstructionData getMyData() {
		return myData;
	}

	public void setMyData(InstructionData myData) {
		this.myData = myData;
	}

}