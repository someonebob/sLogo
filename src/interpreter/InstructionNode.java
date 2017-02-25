package interpreter;

import java.util.ArrayList;
import java.util.List;

import instruction.Instruction;
import util.InstructionSplitter;

/**
 * This class represents the nodes used in the parsed string tree. This tree
 * will hold the nodes (each representing its own individual word) in an order
 * conveying the argument relationship between subsequent nodes.
 * 
 * @author maddiebriere
 *
 */

public class InstructionNode {
	private String myText;
	private String myValue;
	private int myReturn;
	private List<InstructionNode> myChildren;

	public InstructionNode() {
		myText = "";
		myValue = "";
		myChildren = new ArrayList<InstructionNode>();
	}

	public InstructionNode(String text, List<InstructionNode> children) {
		if (text.isEmpty()) {
			// TODO: Error checking
		}
		myText = text;
		myChildren = children;
		myValue = InstructionSplitter.getInstructionStrings(text).get(0);
	}


	/**
	 * Total text return. Example: "forward forward 50" 1st node: "forward
	 * forward 50" 2nd node: "forward 50" 3rd node: "50"
	 * 
	 * @return String of text representing current node
	 */
	public String getMyText() {
		return myText;
	}

	/**
	 * Return only the head value (current node)
	 * 
	 * @return The word contained in this node (e.g., 50, fd, back)
	 */
	public String getMyValue() {
		return myValue;
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

	/**
	 * This returns the number returned by this instruction. All Instructions
	 * must return a value, regardless of their function. For instance, 'fd 50'
	 * will return 50. Hence, the nodes fd and 50 will both have the return 50.
	 * 
	 * @return
	 */
	public int getMyReturn() {
		return myReturn;
	}

}
