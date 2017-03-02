package instruction.Miscellaneous;

import java.util.List;

import instruction.InstructionData;
import user_structures.Variable;

/**
 * Class to perform a certain action
 * from 1 - the given limit.
 * 
 * Assumption: The limit passed to this function is a constant
 * and not an expression -- hopefully change with future
 * adaptations.
 * 
 * @author maddiebriere
 *
 */
public class DoTimes extends Miscellaneous {
	
	public DoTimes(InstructionData instructionData, List<String> args, String myText) {
		super(instructionData, args, myText);
	}

	@Override
	public double execute() {
		int executed=0;
		//Assumption made that limit is constant
		String [] args = getArgumentString(0).split(" ");
		Variable counter = new Variable(args[0], 1);
		//TODO: Error throwing
		getInstructionData().addVariable(counter);
		double limit = Integer.parseInt(args[1]);
		if(limit <=0) {return executed;}
		while(counter.getValue()<=limit){
			executed=1; //at least one loop executed
			runListCommands(1);
			counter.setValue(counter.getValue()+1);
		}
		return executed;
	}

}
