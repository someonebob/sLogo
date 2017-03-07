package instruction.Miscellaneous;

import java.util.List;

import instruction.Instruction;
import instruction.InstructionData;
import interpreter.Interpreter;
import user_structures.VariableData;
import util.MathUtil;

/**
 * Superclass of all Miscellaneous Instructions, corresponding to the
 * variables/control structures/user-defined commands table. Maddie and I
 * authored all subclasses.
 * @author Matthew Barbano
 * @author Maddie Briere
 *
 */
public abstract class Miscellaneous extends Instruction {
	
	public Miscellaneous(InstructionData instructionData, List<String> args, String myText) {
		super(instructionData, args, myText);
	}
	
	protected double runListCommands(int argumentNumber) {
		//TODO Need to change when decide on way to set language (possibly through InstructionData)
		Interpreter listInterpreter = new Interpreter(getInstructionData());
		System.out.println(getArgumentString(argumentNumber));
		return listInterpreter.parseAndRun(getArgumentString(argumentNumber));
	}
	
	/**
	 * Assuming successful execution of loop, counter's value is one past the number of iterations.
	 * @param counter
	 * @param limit
	 * @param increment
	 * @return
	 */
	protected double iterateThroughLoop(VariableData counter, double limit, double increment){
		double lastReturnedValue = 0.0;
		while(MathUtil.doubleLessThanEquals(counter.getValue(), limit)){
			lastReturnedValue = runListCommands(1);
			counter.setValue(counter.getValue() + increment);
		}
		counter.setValue(counter.getValue()-1);
		return lastReturnedValue;
	}
	
	protected VariableData initializeVariable(String name, double value){
		VariableData variable = getInstructionData().containsVariable(name);
		if(variable == null){
			variable = new VariableData(name, value);
			getInstructionData().getVariables().add(variable);
		}
		else{
			variable.setValue(value);
		}
		return variable;
	}
}
