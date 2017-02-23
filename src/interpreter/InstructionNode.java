package interpreter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import instruction.Instruction;
import util.InstructionSplitter;

/**
 * This class represents the nodes used in the parsed string
 * tree. This tree will hold the nodes (each representing its own
 * individual word) in an order conveying the argument relationship
 * between subsequent nodes.
 * 
 * @author maddiebriere
 *
 */

public class InstructionNode {
	private String myText;
	private String myValue;
	private List<InstructionNode> myChildren;
	
	
	public InstructionNode(){
		myText = "";
		myValue = "";
		myChildren = new ArrayList<InstructionNode>();
	}
	
	public InstructionNode(String text, List<InstructionNode> children){
		if(text.isEmpty()){
			//TODO: Error checking
		}
		myText = text;
		myChildren = children;
		myValue = InstructionSplitter.getInstructionStrings(text).get(0);
	}
	
	/**
	 * Returns the correct instruction corresponding to the
	 * current InstructionNode
	 * 
	 * @param toParse
	 * @return functional Instruction representing this InstructionNode
	 */
	public Instruction generateCommand(String toParse){
		//TODO: Complete
		return null;
	}
	
	public String getMyText() {
		return myText;
	}
	
	/**
	 * @return The word contained in this node (e.g., 50, fd, back)
	 */
	public String getMyValue(){
		return myValue;
	}
	
	public List<InstructionNode> getMyChildren() {
		return myChildren;
	}
	
	
}
