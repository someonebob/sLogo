package instruction.MathOperations;

import java.util.List;

import instruction.InstructionData;

public class Cosine extends MathOperation{
	public Cosine(InstructionData data,  List<String> args, String myText) {
		super(data, args, myText);
	}
	
	@Override
	public double execute() {
		return Math.cos(Math.toRadians(getArgumentDouble(0)));
	}

}
