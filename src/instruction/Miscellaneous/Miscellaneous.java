package instruction.Miscellaneous;

import java.util.List;

import instruction.ActorSpecificInstruction;
import instruction.Instruction;
import instruction.InstructionData;
import user_structures.VariableData;
import util.MathUtil;

/**
 * Superclass of all Miscellaneous Instructions, corresponding to the
 * variables/control structures/user-defined commands table.
 * 
 * These are notably the more complex commands, as they require major workspace
 * modification and iteration/post-instruction creation processing.
 * 
 * @author Matthew Barbano
 * @author Maddie Briere
 *
 */
public abstract class Miscellaneous extends Instruction implements ActorSpecificInstruction {

	public Miscellaneous(InstructionData instructionData, List<String> args, String myText) {
		super(instructionData, args, myText);
	}

	/**
	 * Iterates through a loop using the given limit, increment and VariableData
	 * counter. Parses an executes the command held by this Miscellaneous
	 * object.
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

	protected VariableData initializeVariable(String name, double value) {
		VariableData variable = getInstructionData().containsVariable(name);
		if (variable == null) {
			variable = new VariableData(name, value);
			getInstructionData().getVariables().add(variable);
		} else {
			variable.setValue(value);
		}
		return variable;
	}
}
