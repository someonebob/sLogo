package instruction.MultipleTurtleCommands;

import java.util.List;

import instruction.InstructionData;

public class Turtles extends MultipleTurtleCommand {
	public Turtles(InstructionData data, List<String> args, String myText) {
		super(data, args, myText);
	}

	@Override
	public double execute() {
		return getInstructionData().getActorList().size();
	}
}