package instruction.MathOperations;

import java.util.List;

import instruction.Instruction;
import instruction.InstructionData;

public abstract class MathOperation extends Instruction {
	
	public MathOperation(InstructionData data,  List<String> args, String myText) {
		super(data, args, myText);
	}
}
