package instruction.TurtleQueries;

import java.util.List;

import instruction.InstructionData;

public class Showing extends TurtleQuery {

	public Showing(InstructionData instructionData, List<String> args, String myText) {
		super(instructionData, args, myText);
	}

	@Override
	public double execute() {
		return getActiveActor().getShowing() ? 1 : 0;
	}

}
