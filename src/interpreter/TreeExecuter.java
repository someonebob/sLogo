package interpreter;

import java.util.ArrayList;

import exceptions.InvalidCommandException;
import exceptions.WrongArgumentNumberException;
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
	private static final String LIST_START = "ListStart";
	private static final String LIST_END = "ListEnd";
	private static final String GROUP_START = "GroupStart";
	private static final String GROUP_END = "GroupEnd";
	private static final String RESOURCE_INVALID_COMMAND_NAME = "InvalidCommandMessage";
	private static final String RESOURCE_ARGUMENT_NAME = "WrongArgumentNumberMessage";
	
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
	public double execute(InstructionNode head)
	{
		ArrayList<String> args = new ArrayList<String>();
		
		int numberNonNullChildren = 0;
		for(InstructionNode child : head.getMyChildren()){
			if(child != null)
				numberNonNullChildren++;
		}
		
		if(numberNonNullChildren != head.getProperNumArgs()){
			throw new WrongArgumentNumberException(RESOURCE_ARGUMENT_NAME);
		}
		
		for (InstructionNode child : head.getMyChildren()) {
			if (!child.hasRun()) {
				execute(child);
			}
			args.add(child.getMyRunValue());
			
		}
		
		if(!myClass.findShortcutKey(head.getMyValue(), myData).equals("NO MATCH")){
			Instruction i = myClass.generateInstruction(head.getMyValue(), myData, args);
			head.setMyRunValue(""+i.execute()); //Will change with list, for now, just tacks on result
		}
		else{
			throw new InvalidCommandException(RESOURCE_INVALID_COMMAND_NAME);
			//head.setMyRunValue(head.getMyValue());
		}
		
		try{
			return Double.parseDouble(head.getMyRunValue());
		}
		catch(NumberFormatException e){
			return 0.0;
		}
	}


}