package interpreter.builders;

import java.util.ArrayList;
import java.util.List;

import interpreter.misc.InstructionNode;
import interpreter.misc.InstructionTracker;
import interpreter.util.ArgumentReaderUtil;

/**
 * Used in a similar fashion to the ListTreeBuilder and GroupTreeBuider
 * 
 * Allows for the parsing of" to _name_ [ _var_ ] [ _commands_ ] where the name
 * is not yet known (or where the name is already known and must be redefined
 * 
 * @author maddiebriere
 *
 */

public class MakeUserInstructionUtil extends BuilderUtil {

	public MakeUserInstructionUtil(InstructionNode head, InstructionTracker track) {
		super(head, track);
	}

	@Override
	public String construct() {
		addChild(getTrack().removeNext().getMyCommand());
		getTrack().decrementCurrentText();
		getHead().addChildren(buildChildren());
		return getTrack().getCurrentText();
	}

	/**
	 * Create a list of ListStart InstructionNodes holding the text for each
	 * part of the instruction.
	 * 
	 * Example: to dance [ :x ] [ fd :x ] Result: { ListStart{':x'}, ListStart{'fd :x'} }
	 * 
	 * @return List of nodes representing children of head node
	 */
	public List<InstructionNode> buildChildren() {
		ArrayList<InstructionNode> children = new ArrayList<InstructionNode>();
		int numArgs = ArgumentReaderUtil.getNumArgs(getHead(), getTrack().getData());
		for (int i = 1; i < numArgs; i++) {
			InstructionNode child = getTrack().removeNext();
			getTrack().decrementCurrentText();
			children.add(child);
			ListStartUtil list = new ListStartUtil(child, getTrack());
			getTrack().setCurrentText(list.construct());
		}
		return children;
	}
}

