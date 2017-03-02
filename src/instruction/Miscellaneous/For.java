package instruction.Miscellaneous;

import java.util.List;

import instruction.InstructionData;
import user_structures.Variable;

/**
 * Instruction for a for statement
 * 
 * @author maddiebriere
 *
 */

public class For extends Miscellaneous {
	
	public For(InstructionData instructionData, List<String> args, String myText) {
		super(instructionData, args, myText);
	}

	@Override
	public double execute() {
		int executed=0;
		//Assumption made that limit is constant
		String [] args = getArgumentString(0).split(" ");
		Variable counter = new Variable(args[0], Integer.parseInt(args[1]));
		//TODO: Error throwing
		getInstructionData().addVariable(counter);
		double limit = Integer.parseInt(args[2]);
		while(counter.getValue()<limit){
			executed=1; //at least one loop executed
			runListCommands(1);
			counter.setValue(counter.getValue()+Integer.parseInt(args[3]));
		}
		return executed;
	}
}
