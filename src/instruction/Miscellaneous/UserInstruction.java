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
		return listInterpreter.parseAndRun(function.getCommands());
	}
	
	private List<VariableData> generateLocalVars(FunctionData function){
		List<VariableData> localVariables = new ArrayList<VariableData>();
		localVariables.addAll(getInstructionData().getVariables());
		
		if(function == null){
			//TODO: Error-handling
			return null;
		}
		
		//TODO: Make sure this replaces initial value
		for(int i=0; i<function.getArgs().size(); i++){
			VariableData newVar = new VariableData(function.getArgs().get(i), getArgumentDouble(i));
			WorkspaceUpdater.add(localVariables, newVar);
		}
		return localVariables;
	}

}
