package instruction.MathOperations;

import java.util.List;

import exceptions.MathException;
import instruction.InstructionData;
import util.MathUtil;

public class Remainder extends MathOperation{
	private static final String RESOURCE_NOT_INTEGER_NAME = "RemainderMessage";
	
	public Remainder(InstructionData data,  List<String> args) {
		super(data, args);
	}
	
	@Override
	public double execute() {
		if(!MathUtil.hasIntegerValue(getArgumentDouble(0)) || !MathUtil.hasIntegerValue(getArgumentDouble(1))){
			throw new MathException(RESOURCE_NOT_INTEGER_NAME);
		}
		return getArgumentDouble(0) % getArgumentDouble(1);
	}
	


}
