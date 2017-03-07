package instruction.DisplayCommands;

import java.util.List;

import instruction.InstructionData;

public class SetBackground extends DisplayCommand{

	public SetBackground(InstructionData instructionData, List<String> args, String myText) {
		super(instructionData, args, myText);
	}

	@Override
	public double execute() {
		getInstructionData().setBackgroundColorIndex((int)getArgumentDouble(0));
		return getArgumentDouble(0);
	}
	
}
