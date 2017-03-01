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
		double angleInRadians = Math.toRadians(getArgumentDouble(0));
		if(MathUtil.hasIntegerValue((angleInRadians/Math.PI) - 0.5)){
			throw new MathException(RESOURCE_TANGENT_NAME);
		}
		return Math.tan(angleInRadians);
	}
}
