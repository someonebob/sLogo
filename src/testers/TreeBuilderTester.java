package testers;


import java.util.List;

import interpreter.InstructionClassifier;
import interpreter.InstructionNode;
import interpreter.TreeBuilder;

/**
 * Tester to fix bugs in TreeBuilder class and
 * confirm correct output
 * 
 * @author maddiebriere
 *
 */
public class TreeBuilderTester {

	public static void main(String [] args){
		//problem with string value in tree
		//change in node so that no string need be saved
		TreeBuilder build = new TreeBuilder("fd rt left fd 100 fd 100 left 50", new InstructionClassifier("English"));
		List<InstructionNode> headNodes = build.buildTree();
		System.out.println("Single Node, Level-labelled Print:");
		labelledNodePrint(headNodes, 0);
		System.out.println("\nFull Node Print:");
		fullNodePrint(headNodes);
		
	}
	
	/**
	 * Post-order tree traversal to print all values
	 * @param headNodes The nodes built by TreeBuilder
	 * @param level The current tree level
	 */
	private static void labelledNodePrint(List<InstructionNode> headNodes, int level){
		for(InstructionNode curr: headNodes){
			labelledNodePrint(curr.getMyChildren(), level+1);
			System.out.println(String.format("Level: %d   Value: %s", level, curr.getMyValue()));
		}
		
	}
	
	/**
	 * Print of nodes using Instruction Node methods instead, prints
	 * the entire text starting from this node
	 * @param headNodes The nodes built by TreeBuilder
	 * @param level The current tree level
	 */
	private static void fullNodePrint(List<InstructionNode> headNodes){
		for(InstructionNode curr: headNodes){
			System.out.println(String.format(curr.getMyText()));
		}
		
	}
}
