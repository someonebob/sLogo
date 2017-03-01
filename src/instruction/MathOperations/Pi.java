package instruction.MathOperations;

import java.util.List;

import instruction.InstructionData;

public class Pi extends MathOperation{
	public Pi(InstructionData data,  List<String> args, String myText) {
		super(data, args, myText);
	}
	
	@Override
	public double execute() {
		return Math.PI;
	}

}
