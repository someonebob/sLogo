package instruction.TurtleCommands;

import java.util.List;

import instruction.InstructionData;

public class Backward extends TurtleCommand{
	
	public Backward(InstructionData instructionData, List<String> args, String myText) {
		super(instructionData, args, myText);
	}

	@Override
	public double execute() {
		double distance = getArgumentDouble(0);
		checkNegativeArgumentException(distance);
		move(-distance);
		return distance;
	}
}
