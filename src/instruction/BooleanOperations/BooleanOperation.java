package instruction.BooleanOperations;

import java.util.List;

import instruction.Instruction;
import instruction.InstructionData;

public abstract class BooleanOperation extends Instruction {
	public BooleanOperation(InstructionData instructionData,  List<String> args, String myText) {
		super(instructionData, args, myText);
	}
}
