package instruction.Miscellaneous;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import instruction.InstructionData;
import interpreter.Interpreter;
import interpreter.misc.InstructionNode;
import interpreter.util.WorkspaceUpdater;
import user_structures.FunctionData;
import user_structures.VariableData;

/**
 * Separate class to take care of the parsing
 * required when you run a new Instruction. This class
 * parses a given String into Instructions and re-runs
 * the defined command.
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
		FunctionData function = getInstructionData().containsFunction(getMyText());
		if(function == null){
			//TODO: Error-handling
			return 0;
		}
		return runInWorkspace(function);
	}
	
	private double runInWorkspace(FunctionData function){
		List<VariableData> local = generateLocalVars(function);
		InstructionData pre = getInstructionData();
		InstructionData info = new InstructionData(pre.getSimulation(), local, pre.getFunctions(), pre.getLanguage());
		Interpreter listInterpreter = new Interpreter(info);  //Need to change when decide on way to set language
		double ret = listInterpreter.parseAndRun(function.getCommands());
		getInstructionData().getStackVariables(); //pop off stack
		return ret;
	}
	
	private List<VariableData> generateLocalVars(FunctionData function){
		List<VariableData> localVariables = getInstructionData().getVariables();
		for(VariableData v: localVariables){
			double copyVal = v.getValue();
			v.addToStack(copyVal); //add new stack variable for every current variable
			v.setValue(copyVal); //Copy current value into 
		}
		//TODO: Make sure this replaces initial value
		for(int i=0; i<function.getArgs().size(); i++){
			String name = function.getArgs().get(i);
			double value = getArgumentDouble(i);
			WorkspaceUpdater.varAdd(localVariables, name, value);
		}
		return localVariables;
	}

}
