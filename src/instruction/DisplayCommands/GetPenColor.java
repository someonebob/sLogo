package instruction.DisplayCommands;

import java.util.List;

import instruction.InstructionData;

public class GetPenColor extends DisplayCommand{

	public GetPenColor(InstructionData instructionData, List<String> args, String myText) {
		super(instructionData, args, myText);
	}

	@Override
	public double execute() {
		return getInstructionData().getActiveActor().getPenColorIndex();  //TODO Update Jimmy
	}
}
