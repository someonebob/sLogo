package instruction.MathOperations;

import exceptions.SLogoException;
import instruction.InstructionData;
import interpreter.InstructionNode;

public class Quotient extends MathOperation{
	private static int NUM_ARGS = 2;
	public Quotient(InstructionData data, InstructionNode node) {
		super(data, node);
	}

	@Override
	public double execute() {
		if(getArguments().get(1) == 0){
			throw new SLogoException("Error error error!!!");   //NEED TO MODIFY/REFINE EXCEPTION HANDLING WITH A SLOGO EXCEPTION HIERARCHY
		}
		return getArguments().get(0) / getArguments().get(1);
	}

	@Override
	public int getNumArgs() {
		return NUM_ARGS;
	}
}
