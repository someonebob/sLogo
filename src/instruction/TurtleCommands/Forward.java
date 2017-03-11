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
		/*
		System.out.println("ID: " + getActiveActor().getID().getID());
		for(ActorView av : getInstructionData().getActors()){
			System.out.println(av.isTold());
		}
		*/
		System.out.println("------");
		System.out.println("Contents of actor list:");
		for(ActorView av : getInstructionData().getActors()){
			System.out.println("Id: " + av.getID().getID() + " Told: " + av.isTold());
		}
		System.out.println("Active actor " + getInstructionData().getActiveActor());
		System.out.println("------");
		
		
		double distance = getArgumentDouble(0);
		checkNegativeArgumentException(distance);
		move(distance);
		return distance;
	}
}
