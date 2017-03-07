package interpreter.builders;

import java.util.ArrayList;
import java.util.List;

import instruction.InstructionData;
import interpreter.clean.InstructionSplitter;
import interpreter.misc.InstructionNode;

/**
 * Abstract class defining requirements of a BuilderUtil
 * (helper) class -- they class types are used on individual node
 * types and are invoked within the TreeBuilder class.
 * 
 * @author maddiebriere
 *
 */

public abstract class BuilderUtil {
	
	private List<InstructionNode> nodes;
	private InstructionNode head;
	private String current;
	private InstructionData data;
	
	/**
	 * 
	 * @param nodes The current nodes left-over through parsing the current instruction
	 * @param head The head node (the one removed from the slot right in from of the current
	 * 	list of nodes)
	 * 	Example: ( fd 50 50 ) -> head: ( and nodes: {fd, 50, 50, )}
	 * @param current The words in the instruction set that have yet to be processed and put into
	 * a tree
	 * @param data InstructionData object holding information about workspace
	 */
	public BuilderUtil(List<InstructionNode> nodes,
			InstructionNode head, String current, InstructionData data){
		this.nodes=nodes;
		this.head=head;
		this.current=current;
		this.data=data;
	}
	
	/**
	 * This is a method that absolutely MUST be implemented properly by every 
	 * implementing class. This method alters the list of nodes given and
	 * returns an altered current text String to represent the unique processing of 
	 * this particular instruction type. This method essentially grabs the subtree
	 * required for this type of command from the list of nodes and current text.
	 * 
	 * 
	 * Most instructions will invoke the default BuilderUtil
	 * (DefaultUtil) because they can be parsed in a generalized way. Certain instructions, however,
	 * require special parsing to avoid errors and provide the instruction classes with the arguments
	 * they need.
	 * 
	 *  Examples of specialized BuilderUtils include:
	 * 1) GroupStartUtil (the brackets must be removed from the instruction and the instructions
	 * re-ordered according to the grouping of that instruction type
	 * 2) ListStartUtil (anything invoking a list start, like an ifelse, needs to have the
	 * raw text for the instructions held in the list)
	 * 3) MakeUserInstructionUtil (if a command has already been produced, we still want to be able
	 * to redefine it without having the command now act within the declaration as a command)
	 * 
	 * @return The String representing the new current text (re-ordered) -- the instruction minus
	 * the words that have already been accounted for
	 */
	public abstract String construct();

	/**
	 * Create a child for the head node  that
	 * will hold the value created using this Util class
	 * (the re-arranged and parsable text). This child can then
	 * be accessed by the corresponding head node instruction class when
	 * necessary for execution.
	 * 
	 * Ex: Used in GroupStartUtil to place all of commands into child node
	 * for execution as dictated by GroupStart instruction
	 * 
	 * @param value The value (calculated in the construct method) to be
	 * assigned to the new child
	 */
	public void addChild(String value){
		InstructionNode child = new InstructionNode();
		child.setMyRunValue(value);
		ArrayList<InstructionNode> newChildren = new ArrayList<InstructionNode> ();
		newChildren.add(child);
		getHead().setMyChildren(newChildren);
	}
	
	public InstructionNode removeNext(){
		return getNodes().remove(0);
	}
	
	public InstructionNode peekNext(){
		return getNodes().get(0);
	}
	
	public boolean isEmpty(){
		return getNodes().isEmpty();
	}
	
	public void decrementCurrentText(){
		setCurrent(InstructionSplitter.removeFirstItem(getCurrent()));//remove node from current text
	}
	
	public List<InstructionNode> getNodes() {
		return nodes;
	}

	public void setNodes(List<InstructionNode> nodes) {
		this.nodes = nodes;
	}

	public InstructionNode getHead() {
		return head;
	}

	public void setHead(InstructionNode head) {
		this.head = head;
	}

	public String getCurrent() {
		return current;
	}

	public void setCurrent(String current) {
		this.current = current;
	}

	public InstructionData getData() {
		return data;
	}

	public void setData(InstructionData data) {
		this.data = data;
	}
	
	
	
}
