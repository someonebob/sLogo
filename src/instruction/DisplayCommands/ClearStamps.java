package instruction.DisplayCommands;

import java.util.List;

import instruction.InstructionData;
import view.TurtleView;

public class ClearStamps extends DisplayCommand {

	public ClearStamps(InstructionData instructionData, List<String> args, String myText) {
		super(instructionData, args, myText);
	}

	@Override
	protected double execute() {
		List<TurtleView> stamps = getInstructionData().getStamps();
		int returnValue = stamps.isEmpty() ? 0 : 1;
		stamps.removeAll(stamps);
		return returnValue;
	}

}
