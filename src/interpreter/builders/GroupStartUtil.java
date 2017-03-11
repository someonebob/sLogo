package interpreter.builders;

import java.util.List;

import interpreter.misc.InstructionNode;
import interpreter.misc.InstructionTracker;
import interpreter.util.ArgumentReaderUtil;
import interpreter.util.GroupReaderUtil;

/**
 * Subclass of BuilderUtil that processes the current
 * text and nodes when the parser stumbles upon a GroupStart
 * command. This class processes up to the end of the Grouping (the
 * GroupEnd) and returns the nodes and current text with those sections
 * removed, and the processed instructions inside the grouping
 * saved within the head node.
 * 
 * @author maddiebriere
 *
 */

public abstract class GroupStartUtil extends BuilderUtil{
	private final static String END = "GroupEnd";
	private final static String START = "GroupStart";
	private final static String LAYER = "Layer";
	
	public GroupStartUtil(InstructionNode head, InstructionTracker track){
		super(head, track);
	}
	
	/**
	 * Work with the same commands as in Group brackets ( ), but
	 * rearrange it so that the initial TreeBuilder can interpret it. Does not
	 * parse the text -- only rearranges for parsing in the main TreeBuilder. It
	 * stores the altered text (from with in the brackets)
	 *  in a child node of the GroupStart head node, for 
	 * parsing (called in the GroupStart instruction class). 
	 * 
	 * @return The String representing the new current text (re-ordered)
	 */
	public String construct() {
		InstructionNode next = getTrack().removeNext();
		getTrack().decrementCurrentText();
		String value;
		
		//TODO: Replace with abstract factory use
		String instruction = next.getMyCommand();
		String type = next.getMyClassification();
		int numArgs = ArgumentReaderUtil.getNumArgs(next, getTrack().getData());
		
		String group = GroupReaderUtil.getGroup(type);
		if(group.equals(LAYER)){
			value = layerArguments(instruction, numArgs);
		}
		else{
			value = multipleArguments(instruction, numArgs);
		}
		addChild(value);
		
		return getTrack().getCurrentText();
	}
	
	
	public abstract String rearrangeWords(String instruction, int numArgs);
	
	/**
	 * WAY 1: The first way for Groups to be parsed is by layering arguments.
	 * One instruction that follows this pattern is "sum"
	 * This method would transform: ( sum 10 20 30 40 )
	 * Into: sum sum sum 10 20 30 40
	 * Which our current parser can read
	 * 
	 * TODO: Clean up implementation
	 * 
	 * @param instruction The name of the head instruction in the group (above, it
	 * would be sum)
	 * @param numArgs The number of arguments that head instruction takes (needed for 
	 * the layering)
	 * @return A String representing the new value for the child node
	 */
	private String layerArguments(String instruction, int numArgs){
		String value = "";
		int grouping = numArgs - 1;
		List<InstructionNode> constants = getTrack().countAndRemoveArgs(END);
		
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
	 * WAY 2: The second way for Groups to be parsed is by treating the arguments
	 * as multiple commands.
	 * 
	 * One instruction that follows this pattern is "fd"
	 * This method would transform: ( fd 10 20 30 40 )
	 * Into: fd 10 fd 20 fd 30 fd 40
	 * Which our current parser can read
	 * 
	 * TODO: Clean up implementation
	 * 
	 * @param instruction The name of the head instruction in the group (above, it
	 * would be sum)
	 * @param numArgs The number of arguments that head instruction takes (needed for 
	 * the layering)
	 * @return A String representing the new value for the child node
	 */
	private String multipleArguments(String instruction, int numArgs){
		String value = "";
		while(true){
			InstructionNode currNode;
			String name = "";

			for(int i=0; i<numArgs; i++){
				if(!getTrack().isEmpty()){
					currNode = getTrack().removeNext();
					getTrack().decrementCurrentText();
					name = currNode.getMyClassification();
					if(name.equals(END)){
						break;
					}
					if(i == 0){
						value += instruction + " "; //only add in first iteration
					}
					value += currNode.getMyCommand() + " ";
				}
			}
			if(name.equals(END)){
				break;
			}
		}
		value = removeSpace(value);
		return value;
	}
	
	
	/**
	 * Removes the last space from the given string and returns the result
	 * @param value String to modify
	 * @return String with space removed
	 */
	private static String removeSpace(String value){
		if(!value.isEmpty()){
			value = value.substring(0, value.length()-1);
		}
		return value;
	}
	
	public static String getStartBracket() {
		return START;
	}

	public static String getEndBracket() {
		return END;
	}

}
