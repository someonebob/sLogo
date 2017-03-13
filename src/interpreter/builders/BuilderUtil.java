package interpreter.builders;

import java.util.ArrayList;
import interpreter.misc.InstructionNode;
import interpreter.misc.InstructionTracker;

/**
 * Abstract class defining requirements of a BuilderUtil (helper) class -- they
 * class types are used on individual node types and are invoked within the
 * TreeBuilder class.
 * 
 * @author maddiebriere
 *
 */
public abstract class BuilderUtil {

	private InstructionNode head;
	private InstructionTracker track;

	/**
	 * @param head
	 *            The head node (the one removed from the slot right in from of
	 *            the current list of nodes) Example: ( fd 50 50 ) -> head: (
	 *            and nodes: {fd, 50, 50, )}
	 * @param track
	 *            An InstructionTracker holding information about the
	 *            instruction and the workspace in which it was initialized
	 * 
	 * 
	 */
	public BuilderUtil(InstructionNode head, InstructionTracker track) {
		this.head = head;
		this.track = track;
	}

	/**
	 * This is a method that absolutely MUST be implemented properly by every
	 * implementing class. This method alters the list of nodes given and
	 * returns an altered current text String to represent the unique processing
	 * of this particular instruction type. This method essentially grabs the
	 * subtree required for this type of command from the list of nodes and
	 * current text.
	 * 
	 * 
	 * Most instructions will NOT invoke a BuilderUtil
	 * because they can be parsed in a generalized way. Certain instructions,
	 * however, require special parsing to avoid errors and provide the
	 * instruction classes with the arguments they need.
	 * 
	 * Examples of specialized BuilderUtils include: 
	 * 
	 * 1) GroupStartUtil (the brackets must be removed from the instruction and 
	 * the instructions re-ordered according to the grouping of that instruction type 
	 * 
	 * 2) ListStartUtil (anything invoking a list start, like an ifelse, needs to
	 * have the raw text for the instructions held in the list) 
	 * 
	 * 3) MakeUserInstructionUtil (if a command has already been produced, we still
	 * want to be able to redefine it without having the command now act within
	 * the declaration as a command)
	 * 
	 * @return The String representing the new current text (re-ordered) -- the
	 *         instruction minus the words that have already been accounted for
	 */
	public abstract String construct();

	/**
	 * Create a child for the head node that will hold the value created using
	 * this Util class (the re-arranged and parsable text). This child can then
	 * be accessed by the corresponding head node instruction class when
	 * necessary for execution.
	 * 
	 * Ex: Used in GroupStartUtil to place all of commands into child node for
	 * execution as dictated by GroupStart instruction
	 * 
	 * @param value
	 *            The value (calculated in the construct method) to be assigned
	 *            to the new child
	 */
	public void addChild(String value) {
		InstructionNode child = new InstructionNode();
		child.setMyRunValue(value);
		ArrayList<InstructionNode> newChildren = new ArrayList<InstructionNode>();
		newChildren.add(child);
		getHead().setMyChildren(newChildren);
	}

	public InstructionNode getHead() {
		return head;
	}

	public void setHead(InstructionNode head) {
		this.head = head;
	}

	public InstructionTracker getTrack() {
		return track;
	}

	public void setTrack(InstructionTracker track) {
		this.track = track;
	}

}