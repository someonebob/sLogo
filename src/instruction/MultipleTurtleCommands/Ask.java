package instruction.MultipleTurtleCommands;

import java.util.List;

import instruction.InstructionData;

public class Ask extends MultipleTurtleCommand {
	public Ask(InstructionData data, List<String> args, String myText) {
		super(data, args, myText);
	}

	@Override
	public double execute() {
		return 0;
	}
}
