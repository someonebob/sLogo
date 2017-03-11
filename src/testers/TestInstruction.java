package testers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import exceptions.NonsensicalArgumentException;
import instruction.Instruction;
import instruction.InstructionData;
import view.ActorView;

public abstract class TestInstruction extends Instruction{
	private static final String RESOURCE_INVALID_ID_NAME = "InvalidIDMessage";
	
	public TestInstruction(InstructionData data, List<String> args, String myText) {
		super(data, args, myText);
	}
	
	public double execute(){
		System.out.println("------");
		System.out.println("Contents of actor list:");
		for(ActorView av : getInstructionData().getActorList()){
			System.out.println("Id: " + av.getID().getID() + " Told: " + av.isTold());
		}
		System.out.println("Active actor " + getInstructionData().getActiveActor());
		System.out.println("------");
		return 0;
	}
	
}
