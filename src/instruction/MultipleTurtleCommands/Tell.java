package instruction.MultipleTurtleCommands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import exceptions.InvalidIndexException;
import exceptions.NonsensicalArgumentException;
import instruction.InstructionData;
import view.ActorView;

/**
 * Two versions of Tell exist:
 * 1) tells all turtles up to and including the argument
 * 2) tells only those specifically specified by the argument
 * The default is version (2). To use version (1), I will implement another
 * Instruction, TellUpTo.
 * @author Matthew Barbano
 *
 */
public class Tell extends MultipleTurtleCommand {
	private static final String RESOURCE_INVALID_ID_NAME = "InvalidIDMessage";
	
	public Tell(InstructionData data, List<String> args, String myText) {
		super(data, args, myText);
	}

	@Override
	public double execute() {
		//Get list argument as string of numbers (ex: "1 2 3") and convert to array
		//TODO Error checking: IDs are nonnegative integers (InvalidIndexException)
		//System.out.println("Arg string: " + getArgumentString(0));
		String[] idsAsStrings = getArgumentString(0).split("\\s+");
		List<Integer> idsAsInts = new ArrayList<>();
		for(String idString : idsAsStrings){
			try{
				int candidateInt = Integer.parseInt(idString);
				if(candidateInt < 0){
					throw new NonsensicalArgumentException(RESOURCE_INVALID_ID_NAME);
				}
				else{
					idsAsInts.add(candidateInt);
				}
			}
			catch(NumberFormatException exception){
				throw new NonsensicalArgumentException(RESOURCE_INVALID_ID_NAME);
			}
		}
		
		List<ActorView> actorList = getInstructionData().getActors();
		//Set told for all actors to false
		getInstructionData().getSimulation().setTold(new ArrayList<>());
		//Set told for those actors specified in argument list to true
		getInstructionData().getSimulation().setTold(idsAsInts);
		//If not in actor list, then add it/draw it
		for(Integer id : idsAsInts){
			 if(id >= actorList.size()){
				 getInstructionData().getSimulation().newActor();
			 }
		}
		//TODO Change implementation so actorList can hold ids in any order
		//Example: currently 3 actors, then do (0-indexing) Tell [ 0 1 2 7 10 ] - the 7 and 10
		return idsAsInts.get(idsAsInts.size() - 1);
	}
}
