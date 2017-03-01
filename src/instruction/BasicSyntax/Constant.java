package instruction.BasicSyntax;

import java.util.List;

import instruction.Instruction;
import instruction.InstructionData;

public class Constant extends Instruction {

	public Constant(InstructionData instructionData, List<String> args) {
		super(instructionData, args);
	}

	@Override
	public double execute() {
		return 0;
	}

}
