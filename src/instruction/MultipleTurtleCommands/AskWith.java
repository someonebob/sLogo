package instruction.MultipleTurtleCommands;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import instruction.InstructionData;
import util.MathUtil;
import view.ActorView;

public class AskWith extends MultipleTurtleCommand {
	public AskWith(InstructionData data, List<String> args, String myText) {
		super(data, args, myText);
	}

	@Override
	public double execute() {
		/* Save List<Integer> savedToldActors (SAME AS BEFORE)
		 * 
		 * for all actors in existence:
		 * - Set that actor to told and all others to untold
		 * - execute condition for that actor and get result - store in idsAsInts
		 * 
		 *  REST IS SAME AS BEFORE
		 * - Set told based on idsAsInts
		 * - execute [ command(s) ] by making new Interpreter
		 * - Restore old tolds - possibly have new turtles than from before, but told = true only for old turtles
		 * - Return last executed command's return value
		 */
		
		//Save List<Integer> savedToldActors
		Set<Integer> savedToldActors = new TreeSet<>();
		for(ActorView actor : getInstructionData().getActorList()){
			if(actor.isTold()){
				savedToldActors.add(actor.getID().getID());
			}
		}
		
		/*
		for each actor in existence:
			 * - Set that actor to told and all others to untold
			 * - execute condition for that actor and get result - store in idsAsInts
		*/
		List<Integer> idsAsInts = new ArrayList<>();
		for(ActorView actor : getInstructionData().getActorList()){
			Collection<Integer> actorToCheck = new TreeSet<>();
			actorToCheck.add(actor.getID().getID());
			handleTolds(new TreeSet<>());
			double conditionValue = runListCommands(0);
			if(!MathUtil.doubleEquals(conditionValue, 0.0)){
				idsAsInts.add(actor.getID().getID());
			}
		}
		
		//Set told for arg list
			//Set all tolds to 0
			//Activate proper tolds
			//Make new turtles if necessary
		handleTolds(idsAsInts);
		//Execute commands for arg list (use new Interpreters)
		double returnValue = runListCommands(1);
		//Restore old tolds - possibly have new turtles than from before, but told = true only for old turtles
		handleTolds(savedToldActors);
		//Return last executed command's return value
		return returnValue;
		
		/*
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
		//Execute commands for arg list (use new Interpreters)
		double returnValue = runListCommands(1);
		//Restore old tolds - possibly have new turtles than from before, but told = true only for old turtles
		handleTolds(savedToldActors);
		//Return last executed command's return value
		return returnValue;
		*/
	}
}