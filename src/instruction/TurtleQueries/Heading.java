package instruction.TurtleQueries;

import java.util.List;

import instruction.InstructionData;

public class Heading extends TurtleQuery {

	public Heading(InstructionData instructionData, List<String> args, String myText) {
		super(instructionData, args, myText);
	}

	@Override
	public double execute() {
		return getActiveActor().getHeading();
	}

}
