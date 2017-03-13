package interpreter.misc;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the nodes used in the parsed string tree. This tree
 * will hold the nodes (each representing its own individual word) in an order
 * conveying the argument relationship between subsequent nodes.
 * 
 * @author maddiebriere
 *
 */
public class InstructionNode {

	private String myClassification; // The String representing the type
	private String myCommand; // String command
	private List<InstructionNode> myChildren;
	private String myRunValue; // This is used for tree traversal, to check if a
								// command has been executed already

	public InstructionNode() {
		this("", "", new ArrayList<InstructionNode>());
	}

	public InstructionNode(String clss, String value) {
		this(clss, value, new ArrayList<InstructionNode>());
	}

	public InstructionNode(String clss, String value, List<InstructionNode> children) {
		myClassification = clss;
		myCommand = value;
		myChildren = children;
		myRunValue = "NO RUN"; // default
	}

	/**
	 * Pieces together a String from the current Node using the current Node and
	 * all of its children.
	 * 
	 * @return String representing the entire Node
	 */
	public String getMyText() {
		String inst = recursivePrint(this, "");
		return inst.substring(0, inst.length() - 1);// Exclude final space
	}

	private String recursivePrint(InstructionNode node, String curr) {
		curr += node.getMyCommand() + " ";
		for (InstructionNode child : node.getMyChildren()) {
			curr = recursivePrint(child, curr);
		}
		return curr;
	}

	/**
	 * Return only the head value (current node)
	 * 
	 * @return The word contained in this node (e.g., 50, fd, back)
	 */
	public String getMyCommand() {
		return myCommand;
	}

	/**
	 * Check if this node has been evaluated
	 * 
	 * @return true if the run value is not equal to NO RUN (the default), which
	 *         means the instruction has been run, false otherwise
	 */
	public boolean hasRun() {
		return !myRunValue.equals("NO RUN");
	}

	/**
	 * Get all of the nodes attached to the current InstructionNode. Allows for
	 * traversal of the InstructionNode tree
	 * 
	 * @return child nodes of the current InstructionNode
	 */
	public List<InstructionNode> getMyChildren() {
		return myChildren;
	}

	public void addChildren(List<InstructionNode> children) {
		myChildren.addAll(children);
	}

	public String getMyClassification() {
		return myClassification;
	}

	public void setMyClassification(String myClassification) {
		this.myClassification = myClassification;
	}

	public void setMyCommand(String myValue) {
		this.myCommand = myValue;
	}

	public void setMyChildren(List<InstructionNode> myChildren) {
		this.myChildren = myChildren;
	}

	public String getMyRunValue() {
		return myRunValue;
	}

	public void setMyRunValue(String myRunValue) {
		this.myRunValue = myRunValue;
	}

}