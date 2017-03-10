package instruction.BasicSyntax;

import java.util.List;

import instruction.Instruction;
import instruction.InstructionData;
import user_structures.VariableData;

/**
 * Variable class for variables in workspace
 * @author maddiebriere
 *
 */

public class Variable extends BasicSyntax{

	public Variable(InstructionData instructionData, List<String> args, String myText) {
		super(instructionData, args, myText);
	}

	@Override
	public double execute() {
		VariableData name = getInstructionData().containsVariable(getMyText().substring(1));
		if(name!=null){
			return name.getValue();
		}
		else{
			return 0;
		}
	}

}
