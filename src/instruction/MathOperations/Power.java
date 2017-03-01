package instruction.MathOperations;

import java.util.List;

import exceptions.MathException;
import instruction.InstructionData;
import util.MathUtil;

public class Power extends MathOperation{
	private static final String RESOURCE_POWER_NAME = "PowerMessage";
	public Power(InstructionData data,  List<String> args, String myText) {
		super(data, args, myText);
	}
	
	@Override
	public double execute() {
		if(MathUtil.doubleLessThan(getArgumentDouble(0), 0.0) && !MathUtil.hasIntegerValue(getArgumentDouble(1))){
			throw new MathException(RESOURCE_POWER_NAME);
		}
		return Math.pow(getArgumentDouble(0), getArgumentDouble(1));
	}

}
