package instruction.MathOperations;

import java.util.List;

import exceptions.MathException;
import instruction.InstructionData;

public class Quotient extends MathOperation{
	
	private static final String RESOURCE_QUOTIENT_NAME = "QuotientMessage";

	public Quotient(InstructionData data,  List<String> args) {
		super(data, args);
	}

	@Override
	public double execute() {
		if(getArgumentsDouble().get(1) == 0){
			throw new MathException(RESOURCE_QUOTIENT_NAME);
		}
		return getArgumentDouble(0) / getArgumentDouble(1);
	}
}
