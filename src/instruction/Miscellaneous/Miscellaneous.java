package instruction.Miscellaneous;

import java.util.List;

import instruction.Instruction;
import instruction.InstructionData;
import interpreter.Interpreter;
import user_structures.Variable;
import util.MathUtil;

public abstract class Miscellaneous extends Instruction {
	
	public Miscellaneous(InstructionData instructionData, List<String> args, String myText) {
		super(instructionData, args, myText);
	}
	
	protected double runListCommands(int argumentNumber) {
		//TODO Need to change when decide on way to set language (possibly through InstructionData)
		Interpreter listInterpreter = new Interpreter(getInstructionData(), "English");  
		System.out.println("here");
		return listInterpreter.parseAndRun(getArgumentString(argumentNumber));
	}
	
	/**
	 * Assuming successful execution of loop, counter's value is one past the number of iterations.
	 * @param counter
	 * @param limit
	 * @param increment
	 * @return
	 */
	protected double iterateThroughLoop(Variable counter, double limit, double increment){
		double lastReturnedValue = 0.0;
		System.out.println("Limit = " + limit);
		while(MathUtil.doubleLessThanEquals(counter.getValue(), limit)){
			lastReturnedValue = runListCommands(1);
			counter.setValue(counter.getValue() + increment);
			System.out.println(counter.getValue());
		}
		return lastReturnedValue;
	}
	
	protected Variable initializeVariable(String name, double value){
		Variable variable = getInstructionData().containsVariable(name);
		if(variable == null){
			variable = new Variable(name, value);
			getInstructionData().getVariables().add(variable);
		}
		else{
			variable.setValue(value);
		}
		return variable;
	}
}
