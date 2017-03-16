package instruction.BasicSyntax;


import java.util.List;

import instruction.InstructionData;

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

public class ListStart extends BasicSyntax {


	public ListStart(InstructionData data,  List<String> args, String myText){
		super(data, args, myText);
	}
	
	
	@Override
	public double execute() {
		return 0;
	}

}
