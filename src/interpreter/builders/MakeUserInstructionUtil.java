package interpreter.builders;

import java.util.List;

import instruction.InstructionData;
import interpreter.misc.InstructionNode;

/**
 * Used in a similar fashion to the
 * ListTreeBuilder and GroupTreeBuider
 * 
 * Allows for the parsing of"
 * to _name_ [ _var_ ] [ _commands_ ] where 
 * the name is not yet known (or where the name
 * is already known and must be redefined
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
		// TODO Auto-generated method stub
		return null;
	}
}
