package instruction.DisplayCommands;

import java.util.List;

import instruction.InstructionData;

public class ClearStamps extends DisplayCommand {

	public ClearStamps(InstructionData instructionData, List<String> args, String myText) {
		super(instructionData, args, myText);
	}

	@Override
	protected double execute() {
		// TODO Auto-generated method stub
		return 0;
	}

}
