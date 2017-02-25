package interpreter;

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
	
	private String myClassification; // The String representing the type (Forward, Equal, Comment)
	private String myValue; //String command
	private List<InstructionNode> myChildren;

	public InstructionNode() {
		this("", "", new ArrayList<InstructionNode>());
	}
	
	public InstructionNode(String clss, String text) {
		this(clss, text, new ArrayList<InstructionNode>());
	}

	public InstructionNode(String clss, String value, List<InstructionNode> children) {
		if (value.isEmpty()) {
			// TODO: Error checking
		}
		myClassification = clss;
		myValue = value;
		myChildren = children;
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

	public String getMyClassification() {
		return myClassification;
	}

}
