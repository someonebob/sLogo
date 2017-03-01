package instruction.MathOperations;

import java.util.List;

import exceptions.MathException;
import instruction.InstructionData;
import util.MathUtil;

public class Remainder extends MathOperation{
	private static final String RESOURCE_NOT_INTEGER_NAME = "RemainderNotIntegerMessage";
	private static final String RESOURCE_DIVISION_ZERO_NAME = "RemainderDivisionMessage";
	
	public Remainder(InstructionData data,  List<String> args, String myText) {
		super(data, args, myText);
	}
	
	@Override
	public double execute() {
		if(!MathUtil.hasIntegerValue(getArgumentDouble(0)) || !MathUtil.hasIntegerValue(getArgumentDouble(1))){
			throw new MathException(RESOURCE_NOT_INTEGER_NAME);
		}
		if(MathUtil.doubleEquals(getArgumentDouble(1), 0)){
			throw new MathException(RESOURCE_DIVISION_ZERO_NAME);
		}
		return getArgumentDouble(0) % getArgumentDouble(1);
	}
	


}
