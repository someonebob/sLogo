package instruction.TurtleCommands;

import java.util.List;

import instruction.InstructionData;
import view.ActorView;

public class Forward extends TurtleCommand{
	
	public Forward(InstructionData instructionData, List<String> args, String myText) {
		super(instructionData, args, myText);
	}

	@Override
	public double execute() {
		System.out.println("ID: " + getActiveActor().getID().getID());
		for(ActorView av : getInstructionData().getActors()){
			System.out.println(av.isTold());
		}
		double distance = getArgumentDouble(0);
		checkNegativeArgumentException(distance);
		move(distance);
		return distance;
	}
}
