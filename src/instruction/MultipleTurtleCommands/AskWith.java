package instruction.MultipleTurtleCommands;

import java.util.List;

import instruction.InstructionData;

public class AskWith extends MultipleTurtleCommand {
	public AskWith(InstructionData data, List<String> args, String myText) {
		super(data, args, myText);
	}

	@Override
	public double execute() {
		return 0;
	}
}
