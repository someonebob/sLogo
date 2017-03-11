package interpreter.grouptypes;

import java.util.List;

import interpreter.misc.InstructionNode;
import interpreter.misc.InstructionTracker;

/**
 * BuilderUtil of type GroupStart, specific to
 * instructions that must be layered
 * 
 * @author maddiebriere
 *
 */

public class LayerGroupType extends GroupType {

	public LayerGroupType(InstructionTracker track, String instruction, int numArgs) {
		super(track, instruction, numArgs);
	}

	
	/**
	 * WAY 1: The first way for Groups to be parsed is by layering arguments.
	 * One instruction that follows this pattern is "sum"
	 * This method would transform: ( sum 10 20 30 40 )
	 * Into: sum sum sum 10 20 30 40
	 * Which our current parser can read
	 * 
	 * @return A String representing the new value for the child node
	 */
	@Override
	public String rearrangeWords() {
		int numArgs = getNumArgs();
		String instruction = getInstruction();
		
		String value = "";
		int grouping = numArgs - 1;
		List<InstructionNode> constants = getTrack().countAndRemoveArgs(getEndBracket());
		
		//remove inner arguments
		if(constants.size() >= numArgs){
			value = removeCommands(numArgs, constants, value);
		}
		else{
			return value;
		}
		
		while(!constants.isEmpty()){
			value = removeCommands(grouping, constants, value);
			value = instruction + " " + value;
		}
		value = removeSpace(value);
		return value;
	}

}

