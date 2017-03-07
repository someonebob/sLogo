package interpreter.builders;

import java.util.ArrayList;
import java.util.List;

import instruction.InstructionData;
import interpreter.misc.InstructionNode;
import interpreter.util.ArgumentReaderUtil;
import interpreter.util.GroupReader;

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

public class GroupStartUtil extends BuilderUtil{
	private final static String END = "GroupEnd";
	private final static String START = "GroupStart";
	
	public GroupStartUtil(List<InstructionNode> nodes,
			InstructionNode head, String current, InstructionData data){
		super(nodes, head, current, data);
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
		InstructionNode next = removeNext();
		decrementCurrentText();
		String value;
		
		String instruction = next.getMyCommand(); //remove head instruction
		String type = next.getMyClassification();
		int numArgs = getNumArgs(next);
		
		String group = GroupReader.getGroup(type);
		if(group.equals("Layer")){
			value = layerArguments(instruction, numArgs);
		}
		else{
			value = multipleArguments(instruction, numArgs);
		}
		addChild(value);
		
		return getCurrent();
	}
	
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
		List<InstructionNode> constants = countAndRemoveArgs();
		
		//remove inner arguments
		if(constants.size() >= numArgs){
			for(int i=0; i<numArgs; i++){
				InstructionNode cu = constants.remove(0);
				if(!cu.getMyClassification().equals(END))
					value += cu.getMyCommand() + " ";
			}
		}
		else{
			return value;
		}
		
		while(!constants.isEmpty()){
			for(int i=0; i < grouping; i++){
				InstructionNode cu = constants.remove(0);
				if(!cu.getMyClassification().equals(END))
					value += cu.getMyCommand() + " ";
			}
			value = instruction + " " + value;
		}
		value = removeSpace(value);
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
				if(!isEmpty()){
					currNode = removeNext();
					decrementCurrentText();
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
	 * Counts the number of arguments in the grouping and removes the arguments (as well as 
	 * the end bracket) from the list of nodes and current text. 
	 * 
	 * Ex: This method called on the following list of nodes: 
	 * { '(' 'fd' '50' '50' ')' 'fd' '50')}
	 * Would decrement the current text accordingly and return the nodes: 
	 * { 'fd', '50'}
	 * 
	 * @return
	 */
	private  List<InstructionNode> countAndRemoveArgs(){
		List<InstructionNode> toRet = new ArrayList<InstructionNode>();
		while(!isEmpty()){
			decrementCurrentText();
			if(peekNext().equals(END)){
				removeNext(); //remove bracket
				break;
			}
			else{
				toRet.add(removeNext());
			}
		}
		return toRet;
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
