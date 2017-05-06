package instruction.DisplayCommands;

import java.util.List;

import instruction.InstructionData;

public class Stamp extends DisplayCommand {
	
	
	public Stamp(InstructionData instructionData, List<String> args, String myText) {
		super(instructionData, args, myText);
	}

	@Override
	protected double execute() {
		getInstructionData().drawStamp();
		return getActiveImageIndex();
	}

}
