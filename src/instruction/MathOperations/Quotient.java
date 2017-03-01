package instruction.MathOperations;

import exceptions.SLogoException;
import instruction.InstructionData;
import util.MathUtil;

public class Quotient extends MathOperation{
	
	public Quotient(InstructionData data, InstructionNode node) {
		super(data, node);
	}

	@Override
	public double execute() {
		if(MathUtil.doubleEquals(getArgumentsDouble().get(1), 0.0)){
			throw new MathException(RESOURCE_QUOTIENT_NAME);
		}
		return getArgumentDouble(0) / getArgumentDouble(1);
	}
}