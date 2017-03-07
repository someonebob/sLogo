package interpreter.builders;

import java.util.ArrayList;
import java.util.List;

import instruction.InstructionData;
import interpreter.misc.InstructionNode;
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

	public MakeUserInstructionUtil(List<InstructionNode> nodes,
			InstructionNode head, String current, InstructionData data){
		super(nodes,head,current,data);
	}

	@Override
	public String construct() {
		ArrayList<InstructionNode> children = new ArrayList<InstructionNode>();
		addChild(removeNext().getMyCommand());
		decrementCurrentText();
		int numArgs = getNumArgs(getHead());
		for(int i=1; i<numArgs; i++){
			InstructionNode child = removeNext();
			decrementCurrentText();
			children.add(child);
			ListStartUtil list = new ListStartUtil(getNodes(), child, getCurrent(), getData());
			setCurrent(list.construct()); //build numArgs-1 lists
			//TODO: Error throwing
		}
		getHead().addChildren(children);
		return getCurrent();
	}
}
