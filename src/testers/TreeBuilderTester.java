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
		TreeBuilder build = new TreeBuilder("fd fd fd 50", new InstructionClassifier("English"));
		List<InstructionNode> headNodes = build.buildTree();
		for(InstructionNode curr: headNodes){
			System.out.println(curr.getMyText());
		}
	}
}
