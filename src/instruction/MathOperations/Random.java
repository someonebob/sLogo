package instruction.MathOperations;

import java.util.List;

import exceptions.NonsensicalArgumentException;
import instruction.InstructionData;
import util.MathUtil;

public class Random extends MathOperation{
	private static final String RESOURCE_RANDOM_NAME = "RandomMessage";
	
	public Random(InstructionData data,  List<String> args, String myText) {
		super(data, args, myText);
	}
	
	@Override
	public double execute() {
		if(MathUtil.doubleLessThan(getArgumentDouble(0), 0.0)){
			throw new NonsensicalArgumentException(RESOURCE_RANDOM_NAME);
		}
		return Math.random() * getArgumentDouble(0);
	}

}
