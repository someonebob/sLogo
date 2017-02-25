package interpreter;

import java.util.List;

import instruction.Instruction;
/**
 * Builds a tree of InstructionNodes for
 * use in execution of an Instruction
 * 
 * @author maddiebriere
 *
 */

public class TreeBuilder {
	
	/**
	 * This builds a tree of InstructioNodes given a list of Instructions 
	 * (The utility of having Instruction type is to have access
	 * to the number of arguments needed for an instruction)
	 * 
	 * @param instructions A List of InstructionNodes with types and text values, but 
	 * @return A single InstructionNode, the head of the tree
	 */
	public static InstructionNode buildTree(List<InstructionNode> instructions){
		//TODO: Complete
		if(instructions.size()==0){
			return null;
		}
		InstructionNode head = instructions.get(0);
		
		return null;
	}
}
