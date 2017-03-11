package interpreter.grouptypes;

import java.util.List;

import interpreter.misc.InstructionNode;
import interpreter.misc.InstructionTracker;

/**
 * A helper class used in the GroupStartUtil class in order
 * to specify implementation for different types of groupings
 * (e.g., layered vs. multiple commands)
 * 
 * @author maddiebriere
 *
 */

public abstract class GroupType {
	private static final String END = "GroupEnd";
	
	private InstructionTracker track;
	private String instruction;
	private int numArgs;
	
	public GroupType (InstructionTracker track, String instruction, int numArgs){
		this.track = track;
		this.instruction = instruction;
		this.numArgs = numArgs;
	}
	
	public abstract String rearrangeWords();
	
	/**
	 * Repeated loop moved to a helper method to avoid duplicate code.
	 * 
	 * Iterates through the list toModify until the limit is reached by removing the first
	 * item 'limit' times, and concatenating this item's command to the return value
	 * 
	 * @param limit Number of times to remove the first item
	 * @param toModify List of InstructionNodes to modify
	 * @param value Current string value
	 * @return
	 */
	public String removeCommands(int limit, List<InstructionNode> toModify, String value){
		for(int i=0; i<limit; i++){
			InstructionNode cu = toModify.remove(0);
			if(!cu.getMyClassification().equals(END))
				value += cu.getMyCommand() + " ";
		}
		return value;
	}
	
	/**
	 * Removes the last space from the given string and returns the result
	 * @param value String to modify
	 * @return String with space removed
	 */
	protected static String removeSpace(String value){
		if(!value.isEmpty()){
			value = value.substring(0, value.length()-1);
		}
		return value;
	}
	
	public InstructionTracker getTrack() {
		return track;
	}

	public String getInstruction() {
		return instruction;
	}

	public int getNumArgs() {
		return numArgs;
	}
	
	public static String getEndBracket() {
		return END;
	}
	
	
}
