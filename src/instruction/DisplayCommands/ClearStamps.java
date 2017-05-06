package instruction.DisplayCommands;

import java.util.List;

import instruction.InstructionData;

public class ClearStamps extends DisplayCommand {

	public ClearStamps(InstructionData instructionData, List<String> args, String myText) {
		super(instructionData, args, myText);
	}

	@Override
	protected double execute() {
		int returnValue = getInstructionData().getStamps().isEmpty() ? 0 : 1;
		getInstructionData().clearStamps();
		return returnValue;
	}

}
