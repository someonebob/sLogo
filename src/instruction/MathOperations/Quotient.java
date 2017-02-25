package instruction.MathOperations;

import exceptions.SLogoException;
import instruction.InstructionData;
import interpreter.InstructionNode;

public class Quotient extends MathOperation{
	
	public Quotient(InstructionData data, InstructionNode node) {
		super(data, node);
	}

	@Override
	public double execute() {
		if(getArgumentsDouble().get(1) == 0){
			throw new SLogoException("Error error error!!!");   //NEED TO MODIFY/REFINE EXCEPTION HANDLING WITH A SLOGO EXCEPTION HIERARCHY
		}
		return getArgumentDouble(0) / getArgumentDouble(1);
	}
}
