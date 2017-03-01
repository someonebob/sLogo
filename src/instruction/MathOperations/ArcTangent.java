package instruction.MathOperations;

import java.util.List;

import instruction.InstructionData;

public class ArcTangent extends MathOperation{
	public ArcTangent(InstructionData data,  List<String> args, String myText) {
		super(data, args, myText);
	}
	
	@Override
	public double execute() {
		return Math.atan(getArgumentDouble(0));
	}

}
