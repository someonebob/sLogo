package instruction.BasicSyntax;

import java.util.List;

import instruction.Instruction;
import instruction.InstructionData;

public class Constant extends Instruction {

	public Constant(InstructionData instructionData, List<String> args, String myText) {
		super(instructionData, args, myText);
	}

	@Override
	public double execute() {
		return Double.parseDouble(getMyText());
	}

}
