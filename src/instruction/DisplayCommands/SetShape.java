package instruction.DisplayCommands;

import java.util.List;

import instruction.InstructionData;

public class SetShape extends DisplayCommand{

	public SetShape(InstructionData instructionData, List<String> args, String myText) {
		super(instructionData, args, myText);
	}

	@Override
	public double execute() {
		getInstructionData().getActiveActor().setImageByIndex(getArgumentDouble(0));
		return getArgumentDouble(0);
	}

}
