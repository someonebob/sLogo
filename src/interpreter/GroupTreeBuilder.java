package interpreter;

import java.util.ArrayList;
import java.util.List;

import instruction.InstructionData;
import util.ArgumentReaderUtil;

public class GroupTreeBuilder {
	private final static String END = "GroupEnd";
	private final static String START = "GroupStart";

	
	/**
	 * Return the same commands as in Group brackets ( ), but
	 * rearrange it so that the initial TreeBuilder can interpret it.
	 * Ex: ( fd 10 10 20 30 ) --> fd 10 fd 10 fd 20 fd 30
	 * @param nodes Current nodes in the tree builder
	 * @param head The head node of the list
	 * @param current The current String representing the non-parsed parts of the instruction
	 * @param data The InstructionData holding information about the current workspace
	 * @return The String representing the new current text (re-ordered)
	 */
	public static String construct(List<InstructionNode> nodes,
			InstructionNode head, String current, InstructionData data) {
		
		String value = "";
		InstructionNode headNode = nodes.remove(0);
		current = InstructionSplitter.removeFirstItem(current);
		String instruction = headNode.getMyCommand(); //remove head instruction
		String headType = headNode.getMyClassification();
		
		while(!nodes.isEmpty())
		{
			InstructionNode currNode = nodes.get(0);
			String name = currNode.getMyClassification();
			
			if(name.equals(END)){
				break;
			}
			value += instruction + " ";
			currNode = nodes.remove(0);
			
			int numArgs = ArgumentReaderUtil.getNumArgs(headType, instruction, data);
			for(int j=0; j<numArgs; j++){
				if(!nodes.isEmpty()){
					value += currNode.getMyCommand() + " ";
					current = InstructionSplitter.removeFirstItem(current);
				}
			}
		}
		value = removeSpace(value);
		head.setMyRunValue(value);
		
		nodes.remove(0); //remove final bracket (closing)
		current = InstructionSplitter.removeFirstItem(current); //remove bracket from string too
		
		return current;
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
