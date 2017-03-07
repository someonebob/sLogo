package instruction.DisplayCommands;

import java.util.List;

import instruction.InstructionData;

public class SetPenColor extends DisplayCommand{

	public SetPenColor(InstructionData instructionData, List<String> args, String myText) {
		super(instructionData, args, myText);
	}

	@Override
	public double execute() {
		// TODO Auto-generated method stub
		return 0;
	}

}
