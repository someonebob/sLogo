package instruction.Miscellaneous;

import java.util.List;

import instruction.InstructionData;
import interpreter.Interpreter;
import interpreter.util.WorkspaceUpdaterUtil;
import user_structures.FunctionData;
import user_structures.VariableData;

/**
 * <p>
 * <b>SLogo Documentation:</b>Separate class to take care of the parsing
 * required when you run a new Instruction. This class parses a given String
 * into Instructions and re-runs the defined command.
 * </p>
 * 
 * @author maddiebriere
 *
 */

public class UserInstruction extends Miscellaneous {

	/**
	 * Standard 3-argument constructor for the Instruction hierarchy. Through a
	 * series of super() constructor calls up the hierarchy, sets 3
	 * corresponding variables in Instruction. No assumptions cause direct
	 * impact in this constructor. Setting any arguments or entries in args to
	 * null will cause errors elsewhere. Design decision: Making args a List
	 * accommodates SLogo commands with different numbers of arguments.
	 * 
	 * @param instructionData
	 *            for accessing frontend data
	 * @param args
	 *            for storing arguments to SLogo commands
	 * @param myText
	 *            a String representation of this Instruction
	 */
	public UserInstruction(InstructionData instructionData, List<String> args, String myText) {
		super(instructionData, args, myText);
	}

	/**
	 * Executes command as described in class Javadoc comment. Assumptions:
	 * Correct number of SLogo arguments, numerically valued SLogo arguments.
	 * Dependencies: Instruction, InstructionData, Miscellaneous.
	 * 
	 * @return the return value of the final command executed (or 0 if none do)
	 */
	@Override
	public double execute() {
		if (!getInstructionData().containsFunction(getMyText())) {
			return 0;
		}
		return runInWorkspace(getInstructionData().getFunction(getMyText()));
	}

	/**
	 * Run the command now that we know that it exists in the workspace.
	 * 
	 * @param function
	 *            FunctionData to execute
	 * @return The run value returned by this function
	 */
	private double runInWorkspace(FunctionData function) {
		List<VariableData> local = generateLocalVars(function);
		InstructionData info = getInstructionData().replicateSelfWithNewVariables(local);
		Interpreter listInterpreter = new Interpreter(info);
		double ret = listInterpreter.parseAndRun(function.getCommands());
		getInstructionData().getStackVariables(); // pop off stack
		return ret;
	}

	/**
	 * Generate the local variables for this function using information from
	 * this Miscellaneous object.
	 * 
	 * @param function
	 * @return
	 */
	private List<VariableData> generateLocalVars(FunctionData function) {
		List<VariableData> localVariables = getInstructionData().getVariables();
		duplicateCurrentVariables(localVariables);
		addFunctionVariables(localVariables, function);
		return localVariables;
	}

	/**
	 * Make a duplicate of all of the current variables in the workspace for
	 * this "run" of the function. Consider these duplicated variables as a
	 * "layer" of the variable stack.
	 * 
	 * @param localVariables
	 *            The current list of variables to modify
	 */
	private void duplicateCurrentVariables(List<VariableData> localVariables) {
		for (VariableData v : localVariables) {
			double copyVal = v.getValue();
			v.addToStack(copyVal);
			v.setValue(copyVal);
		}
	}

	/**
	 * Modify the top "layer of the stack" by setting the variables created by
	 * the FunctionData equal to the correct values for this "run-through" of
	 * the function.
	 * 
	 * @param localVariables
	 *            The current list of variables to modify
	 * @param function
	 *            The FunctionData object created by this Miscellaneous object
	 */
	private void addFunctionVariables(List<VariableData> localVariables, FunctionData function) {
		for (int i = 0; i < function.getArgs().size(); i++) {
			String name = function.getArgs().get(i);
			double value = getArgumentDouble(i);
			WorkspaceUpdaterUtil.varAdd(localVariables, name, value);
		}
	}

}
