package instruction.BasicSyntax;

import instruction.Instruction;
import instruction.InstructionData;
import interpreter.InstructionNode;

/**
 * Represents character starting a List of commands
 * This is considered a command type that takes any
 * number of arguments as its children (executable). 
 * Execution of this Instruction means executing
 * all of its children.
 * 
 * One of children must be 
 * 
 * @author maddiebriere
 *
 */

public class ListStart extends Instruction {

	public ListStart(){
		super(new InstructionData(), new InstructionNode());
		//TODO: check and complete
	}
	
	@Override
	public double execute() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getNumArgs() {
		// TODO Auto-generated method stub
		return 0;
	}

}
