package instruction.TurtleCommands;

import java.util.List;

import instruction.InstructionData;

public class Forward extends TurtleCommand{
	
	public Forward(InstructionData instructionData, List<String> args, String myText) {
		super(instructionData, args, myText);
	}

	@Override
	public double execute() {
		double distance = getArgumentDouble(0);
		move(distance);
		return distance;
	}
}
