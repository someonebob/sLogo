package instruction.MultipleTurtleCommands;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import instruction.InstructionData;
import view.ActorView;

public class Ask extends MultipleTurtleCommand {
	public Ask(InstructionData data, List<String> args, String myText) {
		super(data, args, myText);
	}

	@Override
	public double execute() {
		//Convert "turtles" arg list to List<Integer> of ids
		List<Integer> idsAsInts = convertStringToIntegerList(getArgumentString(0));
		//Save List<Integer> savedToldActors
		Set<Integer> savedToldActors = new TreeSet<>();
		for(ActorView actor : getInstructionData().getActors()){
			if(actor.isTold()){
				savedToldActors.add(actor.getID().getID());
			}
		}
		//Set told for arg list
			//Set all tolds to 0
			//Activate proper tolds
			//Make new turtles if necessary
		handleTolds(idsAsInts);
		
		for(ActorView av : getInstructionData().getActors()){
			 System.out.println(av.getID().getID() + " " + av.isTold());
		}
		
		//Execute commands for arg list (use new Interpreters)
		double returnValue = runListCommands(1);
		//Restore old tolds - possibly have new turtles than from before, but told = true only for old turtles
		handleTolds(savedToldActors);
		//Return last executed command's return value
		return returnValue;
	}
}
