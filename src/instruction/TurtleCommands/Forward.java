package instruction.TurtleCommands;

import java.util.List;

import instruction.InstructionData;

public class Forward extends TurtleCommand{
	
	public Forward(InstructionData instructionData, List<String> args) {
		super(instructionData, args);
	}

	@Override
	public double execute() {
		double distance = getArgumentDouble(0);
		move(distance);
		return distance;
	}
}
