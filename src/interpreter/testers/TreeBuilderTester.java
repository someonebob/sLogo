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
		String spiral = "to spiral [ :len ] [ if less? :len 200 [ fd :len rt 89 spiral + :len 3 ] ] spiral 10";
		String tree = "to tree [ :length :angle :depth :scale ] [ ifelse equal? :depth 1 [ fd :length bk :length ] [ fd :length lt :angle tree product :length :scale :angle difference :depth 1 :scale rt :angle rt :angle tree product :length :scale :angle difference :depth 1 :scale lt :angle bk :length ] ]";

		TreeBuilder build = new TreeBuilder(spiral,
				new InstructionClassifier("English"), new InstructionData());
		List<InstructionNode> headNodes = build.buildFullTree();
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
