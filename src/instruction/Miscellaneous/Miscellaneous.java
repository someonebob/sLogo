package instruction.Miscellaneous;

import java.util.List;

import instruction.ActorSpecificInstruction;
import instruction.Instruction;
import instruction.InstructionData;
import user_structures.VariableData;
import util.MathUtil;

/**
 * Superclass of all miscellaneous-type Instructions. All of these Instructions
 * either relate to variable creation, control structures, or user-defined
 * commands. It is assumed that instructionData and args are not null, and that
 * args contains the correct number of non-null entries for each Instruction
 * subclass. Dependencies are InstructionData, List, String, and Instruction's
 * 3-argument constructor.
 * 
 * @author Matthew Barbano
 * @author maddiebriere
 *
 */
public abstract class Miscellaneous extends Instruction implements ActorSpecificInstruction {

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
	public Miscellaneous(InstructionData instructionData, List<String> args, String myText) {
		super(instructionData, args, myText);
	}

	/**
	 * Iterates through a loop using the given limit, increment and VariableData
	 * counter. Parses and executes the command held by this Miscellaneous
	 * object. Assumes that counter is not null and that counter.getValue() <=
	 * limit to start. If the latter is not true, the loop does not execute, but
	 * the method still runs.
	 * 
	 * Assuming successful execution of loop, counter's value is one past the
	 * number of iterations.
	 * 
	 * @param counter
	 *            The VariableData object used to keep track of iteration
	 * @param limit
	 *            The limit for the iteration, at which the method halts
	 * @param increment
	 *            The increment by which to increase the counter object
	 * @return A double representing the return value of the last run command
	 */
	protected double iterateThroughLoop(VariableData counter, double limit, double increment) {
		double lastReturnedValue = 0.0;
		while (MathUtil.doubleLessThanEquals(counter.getValue(), limit)) {
			lastReturnedValue = runListCommands(1);
			counter.setValue(counter.getValue() + increment);
		}
		counter.setValue(counter.getValue() - 1);
		return lastReturnedValue;
	}

	/**
	 * Method to initialize variable "safely" (check for its existence in the
	 * current workspace and either add it or update it). Assumes name is not
	 * null.
	 * 
	 * @param name
	 *            The name of the new variable
	 * @param value
	 *            The value of the new variable
	 * @return The new (or updated) variable
	 */
	protected VariableData initializeVariable(String name, double value) {
		VariableData variable = getInstructionData().getVariable(name);
		if (variable == null) {
			variable = new VariableData(name, value);
			getInstructionData().getVariables().add(variable);
		} else {
			variable.setValue(value);
		}
		return variable;
	}
}
