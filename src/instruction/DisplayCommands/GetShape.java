package instruction.DisplayCommands;

import java.util.List;

import instruction.InstructionData;

public class GetShape extends DisplayCommand{

	public GetShape(InstructionData instructionData, List<String> args, String myText) {
		super(instructionData, args, myText);
	}

	@Override
	public double execute() {
		return getInstructionData().getActiveActor().getImageViewIndex();  //TODO Update Jimmy
	}

}
