package interpreter.testers;


import java.util.List;

import instruction.InstructionData;
import interpreter.builders.TreeBuilder;
import interpreter.classification.InstructionClassifier;
import interpreter.misc.InstructionNode;

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
		TreeBuilder build = new TreeBuilder("to dance [ :x ] [ fd :x ]", new InstructionClassifier("English"), new InstructionData());
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
			System.out.println(String.format("Level: %d   Value: %s", level, curr.getMyCommand()));
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
