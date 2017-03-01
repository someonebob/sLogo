package instruction.MathOperations;

import java.util.List;

import instruction.InstructionData;

public class Sine extends MathOperation{
	public Sine(InstructionData data,  List<String> args, String myText) {
		super(data, args, myText);
	}
	
	@Override
	public double execute() {
		return Math.sin(Math.toRadians(getArgumentDouble(0)));
	}

}
