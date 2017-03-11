package interpreter.builders;

import interpreter.misc.InstructionNode;
import interpreter.misc.InstructionTracker;


/**
 * BuilderUtil of type GroupStart, specific to
 * instructions that require the creation
 * of multiple consecutive instructions
 * 
 * @author maddiebriere
 *
 */


public class MultipleGroupStartUtil extends GroupStartUtil {

	public MultipleGroupStartUtil(InstructionNode head, InstructionTracker track) {
		super(head, track);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String rearrangeWords(String instruction, int numArgs) {
		// TODO Auto-generated method stub
		return null;
	}

}
