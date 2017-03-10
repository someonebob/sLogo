package instruction.BasicSyntax;

import java.util.List;

import instruction.ActorSpecificInstruction;
import instruction.Instruction;
import instruction.InstructionData;

public class BasicSyntax extends Instruction {	
	public BasicSyntax(InstructionData instructionData, List<String> args, String myText) {
		super(instructionData, args, myText);
	}

	@Override
	public double execute() {
		return Double.parseDouble(getMyText());
	}

}
