package interpreter.builders;

import java.util.List;

import instruction.InstructionData;
import interpreter.clean.InstructionSplitter;
import interpreter.misc.InstructionNode;
import interpreter.util.ArgumentReaderUtil;
import interpreter.util.GroupReader;
import util.Pair;

public class GroupBuilderUtil {
	private final static String END = "GroupEnd";
	private final static String START = "GroupStart";
	
	
	/**
	 * Return the same commands as in Group brackets ( ), but
	 * rearrange it so that the initial TreeBuilder can interpret it. Does not
	 * parse the text -- only rearranges for parsing in the main TreeBuilder
	 * 
	 * @param nodes Current nodes in the tree builder
	 * @param head The head node of the list
	 * @param current The current String representing the non-parsed parts of the instruction
	 * @param data The InstructionData holding information about the current workspace
	 * @return The String representing the new current text (re-ordered)
	 */
	public static String construct(List<InstructionNode> nodes,
			InstructionNode head, String current, InstructionData data) {
		
		String value;
		InstructionNode headNode = nodes.remove(0);
		current = InstructionSplitter.removeFirstItem(current);
		String instruction = headNode.getMyCommand(); //remove head instruction
		String type = headNode.getMyClassification();
		int numArgs = ArgumentReaderUtil.getNumArgs(type, instruction, data);
		
		Pair <String, String> result;
		String group = GroupReader.getGroup(type);
		if(group.equals("Layer")){
			result = layerArguments(nodes, instruction, current, numArgs);
		}
		else{
			result = multipleArguments(nodes, instruction, current, numArgs);
		}
		
		value = result.getMyA();
		current = result.getMyB();
		head.setMyRunValue(value);
		
		System.out.println(value);
		System.out.println(current);
		
		return current;
	}
	
	//TODO: Fix
	private static Pair<String, String> layerArguments(List<InstructionNode> nodes, 
			String instruction, String current, int numArgs){
		String value = "";
		int grouping = numArgs - 1;
		int i = grouping;
		InstructionNode curr = nodes.get(i);
		
		while(!curr.getMyClassification().equals(END)){
			if (!curr.getMyClassification().equals(END)){
				value = instruction + " " + value;
			}
			else{
				break;
			}
			//TODO: Check for empty list
			for(int j=0; j<grouping; j++){
				if (!curr.getMyClassification().equals(END)){
					curr = nodes.remove(i);
					current = InstructionSplitter.removeFirstItem(current);
					value += curr.getMyCommand() + " ";
					break;
				} 
			}
		} 
		
		
		
		return new Pair<String, String>(value, current);
	}
	
	//TODO: Complete
	private static Pair<String, String> multipleArguments(List<InstructionNode> nodes, 
			String instruction, String current, int numArgs){
		String value = "";
		while(true){
			InstructionNode currNode;
			String name = "";

			for(int i=0; i<numArgs; i++){
				if(!nodes.isEmpty()){
					currNode = nodes.remove(0);
					current = InstructionSplitter.removeFirstItem(current);
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
		return new Pair<String, String>(value, current);
	}
	
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
