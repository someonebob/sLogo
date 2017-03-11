package interpreter.builders;

import interpreter.misc.InstructionNode;
import interpreter.misc.InstructionTracker;

/**
 * BuilderUtil of type GroupStart, specific to
 * instructions that must be layered
 * 
 * @author maddiebriere
 *
 */

public class LayerGroupStartUtil extends GroupStartUtil {

	public LayerGroupStartUtil(InstructionNode head, InstructionTracker track) {
		super(head, track);
	}

/*	@Override
	public String rearrangeWords(String instruction, int numArgs) {
		// TODO Auto-generated method stub
		return null;
	}*/

}

