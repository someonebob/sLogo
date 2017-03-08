package interpreter.execute;

import java.util.ArrayList;
import java.util.List;

import exceptions.InvalidCommandException;
import exceptions.WrongArgumentNumberException;
import instruction.Instruction;
import instruction.InstructionData;
import interpreter.classification.InstructionClassifier;
import interpreter.misc.InstructionNode;
import interpreter.util.ArgumentReaderUtil;

/**
 * Class used to traverse tree and produce runnable instructions. Carries out
 * the instructions head in the InstructionNode head node passed to the class.
 * 
 * TODO: Clean-up
 * 
 * @author maddiebriere
 *
 */
public class TreeExecuter
{

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
		//TODO: Error check without losing functionality
		checkChildren(head);
		List<String> args = buildArguments(head);
		variableCheck(head,args);
		generateHead(head, args);
		return parseReturn(head);
		
	}
	
	/**
	 * Generate the head instruction with the current information if the instruction exists
	 * and set the run value of the InstructionNode to the result of this instruction being
	 * executed, otherwise just set the head run value to the string contained in the command.
	 * @param head Head instruction node
	 * @param args List of arguments
	 */
	private void generateHead(InstructionNode head, List<String> args){
		if(!myClass.getInstructionType(head.getMyCommand(), myData).equals("NO MATCH")){
			Instruction i = myClass.generateInstruction(head.getMyCommand(), myData, args);
			head.setMyRunValue(""+i.execute()); //Will change with list, for now, just tacks on result
		}
		else{ //Unknown value, just set run value to string command
			head.setMyRunValue(head.getMyCommand());
		}
	}
	
	/**
	 * 
	 * TODO: Move this into its own BuilderUtil class? Very out of place right now
	 * 
	 * If this is a variable creation command, make sure that the output has knowledge
	 * of the desired variable name.
	 * @param head InstructionNode at head of tree
	 * @param args String of arguments for instruction
	 */
	private void variableCheck(InstructionNode head, List<String> args){
		if(head.getMyClassification().equals("MakeVariable")){
			args.set(0, head.getMyChildren().get(0).getMyCommand());
		}
	}
	
	private double parseReturn(InstructionNode head){
		try{
			return Double.parseDouble(head.getMyRunValue());
		}
		catch(NumberFormatException e){
			if(myClass.isValid(head.getMyCommand(), myData)){
				return 0.0;
			}
			else{
				throw new InvalidCommandException(RESOURCE_INVALID_COMMAND_NAME);
			}
		}
	}
	
	private List<String> buildArguments(InstructionNode head){
		ArrayList<String> args = new ArrayList<String>();
		
		for (InstructionNode child : head.getMyChildren()) {
			if (!child.hasRun()) {
				execute(child);
			}
			args.add(child.getMyRunValue());
		}
		if(head.hasRun()){ //Add the head value if the whole thing has already run
			args.add(head.getMyRunValue());
		}
		return args;
	}
	
	
	private void checkChildren(InstructionNode head){
		int numberNonNullChildren = 0;
		for(InstructionNode child : head.getMyChildren()){
			if(child != null)
				numberNonNullChildren++;
		}
		
		if(numberNonNullChildren != ArgumentReaderUtil.
				getNumArgs(head.getMyClassification(), head.getMyCommand(), myData)){
			throw new WrongArgumentNumberException(RESOURCE_ARGUMENT_NAME);
		}
	}


}