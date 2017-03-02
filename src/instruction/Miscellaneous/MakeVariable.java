package instruction.Miscellaneous;

import java.util.List;

import instruction.InstructionData;
import user_structures.Variable;

public class MakeVariable extends Miscellaneous {
	
	public MakeVariable(InstructionData instructionData,  List<String> args, String myText) {
		super(instructionData, args, myText);
	}

	@Override
	public double execute(){
		Variable variable = getInstructionData().containsVariable(getArgumentString(0));
		if(variable == null){
			variable = new Variable(getArgumentString(0), getArgumentDouble(1));
			getInstructionData().getVariables().add(variable);
		}
		else{
			variable.setValue(getArgumentDouble(1));
		}
		return getArgumentDouble(1);
	}

	

}

