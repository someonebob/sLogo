package interpreter;

import java.util.List;

import instruction.*;

/**
 * This interface will use a variety of resource files to transform An input
 * string into a useable command (we call it an Instruction). By returning an
 * Instruction, we have transformed something with no functionality Into
 * something capable of executing and returning knowledge about itself.
 * 
 * @maddiebriere
 **/

public class Interpreter {

	private InstructionData myData;
	private InstructionClassifier myClassifier;

	public Interpreter(InstructionData data, String language) {
		myData = data;
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
		List<InstructionNode> headNodes = parse(instruction);
		TreeExecuter executer = new TreeExecuter(getMyData(), getMyClassifier());
		executeTree(executer, headNodes);
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
	private List<InstructionNode> parse(String toParse) {
		TreeBuilder builder = new TreeBuilder(toParse, getMyClassifier());
		List<InstructionNode> headNodes = builder.buildTree();
		return headNodes;
	}
	
	private void executeTree(TreeExecuter executer, List<InstructionNode> headNodes){
		for(InstructionNode node: headNodes){
			executer.execute(node);
		}
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
