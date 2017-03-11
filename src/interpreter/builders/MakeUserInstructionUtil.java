package interpreter.builders;

import java.util.ArrayList;

import interpreter.misc.InstructionNode;
import interpreter.misc.InstructionTracker;
import interpreter.util.ArgumentReaderUtil;

/**
 * Used in a similar fashion to the
 * ListTreeBuilder and GroupTreeBuider
 * 
 * Allows for the parsing of"
 * to _name_ [ _var_ ] [ _commands_ ] where 
 * the name is not yet known (or where the name
 * is already known and must be redefined
 * 
 * TODO: Complete
 * 
 * @author maddiebriere
 *
 */

public class MakeUserInstructionUtil extends BuilderUtil{

	public MakeUserInstructionUtil(InstructionNode head, InstructionTracker track){
		super(head, track);
	}

	@Override
	public String construct() {
		ArrayList<InstructionNode> children = new ArrayList<InstructionNode>();
		addChild(getTrack().removeNext().getMyCommand());
		getTrack().decrementCurrentText();
		int numArgs = ArgumentReaderUtil.getNumArgs(getHead(), getTrack().getData());
		for(int i=1; i<numArgs; i++){
			InstructionNode child = getTrack().removeNext();
			getTrack().decrementCurrentText();
			children.add(child);
			ListStartUtil list = new ListStartUtil(child, getTrack());
			getTrack().setCurrentText(list.construct()); 
		}
		getHead().addChildren(children);
		return getTrack().getCurrentText();
	}
}
