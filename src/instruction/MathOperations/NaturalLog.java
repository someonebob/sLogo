package instruction.MathOperations;

import java.util.List;

import exceptions.MathException;
import instruction.InstructionData;
import util.MathUtil;

public class NaturalLog extends MathOperation{
	private static final String RESOURCE_LOG_NAME = "NaturalLogarithmMessage";
	public NaturalLog(InstructionData data,  List<String> args, String myText) {
		super(data, args, myText);
	}
	
	@Override
	public double execute() {
		if(MathUtil.doubleLessThanEquals(getArgumentDouble(0), 0.0)){
			throw new MathException(RESOURCE_LOG_NAME);
		}
		return Math.log(getArgumentDouble(0));
	}

}
