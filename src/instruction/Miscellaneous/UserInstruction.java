package instruction.Miscellaneous;

import java.util.List;

import instruction.InstructionData;
import interpreter.Interpreter;
import user_structures.Function;
import user_structures.Variable;

/**
 * Separate class to take care of the parsing
 * required when you run a new Instruction.
 * 
 * @author maddiebriere
 *
 */

public class UserInstruction extends Miscellaneous {

	public UserInstruction(InstructionData instructionData, List<String> args, String myText) {
		super(instructionData, args, myText);
	}

	@Override
	public double execute() {
		Function function = getInstructionData().containsFunction(getMyText());
		if(function == null){
			//TODO: Error-handling
			return 0;
		}
		for(int i=0; i<function.getArgs().size(); i++){
			getInstructionData().addVariable(new Variable(function.getArgs().get(i),
					getArgumentDouble(i)));
			//TODO: Error-handling
		}
		Interpreter listInterpreter = new Interpreter(getInstructionData(), "English");  //Need to change when decide on way to set language
		return listInterpreter.parseAndRun(function.getCommands());
	}

}
