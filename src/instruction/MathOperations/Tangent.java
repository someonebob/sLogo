package instruction.MathOperations;

import java.util.List;

import exceptions.MathException;
import instruction.InstructionData;
import util.MathUtil;

public class Tangent extends MathOperation{
	private static final String RESOURCE_TANGENT_NAME = "TangentMessage";

	public Tangent(InstructionData data,  List<String> args, String myText) {
		super(data, args, myText);
	}

	@Override
	public double execute() {
		if(MathUtil.hasIntegerValue(getArgumentDouble(0)/ (Math.PI / 2))){
			throw new MathException(RESOURCE_TANGENT_NAME);
		}
		return Math.tan(getArgumentDouble(0));
	}
}
