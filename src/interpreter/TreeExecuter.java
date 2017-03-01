package interpreter;

import java.util.ArrayList;
import instruction.Instruction;
import instruction.InstructionData;

/**
 * Class used to traverse tree and produce runnable instructions. Carries out
 * the instructions head in the InstructionNode head node passed to the class.
 * 
 * @author maddiebriere
 *
 */
public class TreeExecuter
{

	private InstructionData myData;
	private InstructionClassifier myClass;

	public TreeExecuter(InstructionData data, InstructionClassifier clzz)
	{
		myData = data;
		myClass = clzz;
	}

	/**
	 * Deconstruct a tree and execute using post-traversal
	 * 
	 * TODO: Add in tree/ variable difference -- use the executable boolean to
	 * make these decisions
	 * 
	 * TODO: Testing
	 * 
	 * @param head
	 *            Head node of Instruction tree
	 */
	public void execute(InstructionNode head)
	{
		ArrayList<String> args = new ArrayList<String>();
		for (InstructionNode child : head.getMyChildren()) {
			if (!child.hasRun()) {
				if (!isStart(child)) {
					execute(child);
					args.add(child.getMyRunValue());
				} else {
					args.add(buildInnerList(child));
				}
			}
		}
		Instruction i = myClass.generateInstruction(head.getMyValue(), myData, args);
		System.out.println("HERE");
		head.setMyRunValue("" + i.execute()); // Will change with list, for now,
												// just tacks on result
	}

	/**
	 * Construct an inner list (or grouping) when you want to have knowledge of
	 * items in a list, but don't actually want to run them.
	 * 
	 * @param head
	 */
	private String buildInnerList(InstructionNode head)
	{
		// TODO: Complete
		return null;
	}

	private boolean isStart(InstructionNode child)
	{
		// TODO:Complete
		return false;
	}

}