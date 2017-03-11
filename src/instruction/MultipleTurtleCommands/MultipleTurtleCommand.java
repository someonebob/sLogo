package instruction.MultipleTurtleCommands;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import exceptions.NonsensicalArgumentException;
import instruction.Instruction;
import instruction.InstructionData;
import view.ActorView;

public abstract class MultipleTurtleCommand extends Instruction{
	private static final String RESOURCE_INVALID_ID_NAME = "InvalidIDMessage";
	
	public MultipleTurtleCommand(InstructionData data, List<String> args, String myText) {
		super(data, args, myText);
	}
	
	protected List<Integer> convertStringToIntegerList(String toConvert){
		String[] idsAsStrings = toConvert.split("\\s+");
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
		return idsAsInts;
	}
	
	protected void handleTolds(Collection<Integer> idsAsInts){
		List<ActorView> actorList = getInstructionData().getActors();
		//Set told for all actors to false
		getInstructionData().getSimulation().setTold(new ArrayList<>());
		//Set told for those actors specified in argument list to true
		getInstructionData().getSimulation().setTold(idsAsInts);
		//If not in actor list, then add it/draw it
		for(Integer id : idsAsInts){
			 if(id >= actorList.size()){
				 getInstructionData().getSimulation().newActor();
				 System.out.println("Created new actor, I think");
			 }
		}
	}
	
}
