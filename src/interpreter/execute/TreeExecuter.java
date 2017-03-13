package interpreter.execute;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import exceptions.InvalidCommandException;
import exceptions.WrongArgumentNumberException;
import instruction.Instruction;
import instruction.InstructionData;
import interpreter.classification.InstructionClassifier;
import interpreter.factories.InstructionFactory;
import interpreter.misc.InstructionNode;
import interpreter.util.ArgumentReaderUtil;

/**
 * Class used to traverse tree and produce runnable instructions. Carries out
 * the instructions head in the InstructionNode head node passed to the class.
 * 
 * @author maddiebriere
 *
 */
public class TreeExecuter {
	private static final String RESOURCE_INVALID_COMMAND_NAME = "InvalidCommandMessage";
	private static final String RESOURCE_ARGUMENT_NAME = "WrongArgumentNumberMessage";

	private InstructionData myData;
	private InstructionClassifier myClass;

	public TreeExecuter(InstructionData data, InstructionClassifier clzz) {
		myData = data;
		myClass = clzz;
	}

	/**
	 * Deconstruct a tree and execute using post-traversal. Steps: 1) Check that
	 * the tree is valid (every head node has the correct number of children
	 * given its command type) 2) Create a List of arguments from the
	 * information held in the head node 3) Generate the head instruction using
	 * this information 4) Generate the correct return value for the instruction
	 * 
	 * @param head
	 *            Head node of Instruction tree
	 * @return double representing the return value of the executed instruction
	 */
	public double execute(InstructionNode head) {
		checkChildren(head);
		List<String> args = buildArguments(head);
		variableCheck(head, args);
		generateHead(head, args);
		return parseReturn(head);

	}

	/**
	 * Generate the head instruction with the current information if the
	 * instruction exists and set the run value of the InstructionNode to the
	 * result of this instruction being executed, otherwise just set the head
	 * run value to the string contained in the command.
	 * 
	 * @param head
	 *            Head instruction node
	 * @param args
	 *            List of arguments
	 */
	private void generateHead(InstructionNode head, List<String> args) {
		if (!myClass.getInstructionType(head.getMyCommand(), myData).equals("NO MATCH")) {
			Instruction i = generateInstruction(head.getMyCommand(), myData, args);
			head.setMyRunValue("" + i.executeAllToldTurtles()); 
		} else { // Unknown value, just set run value to string command
			head.setMyRunValue(head.getMyCommand());
		}
	}

	/**
	 * NOTE: This method is a hard-coded solution to the problem of saving a
	 * variable name and is not a desirable fix. Given more time, this would
	 * have been developed into a MakeVariableUtil class.
	 * 
	 * If this is a variable creation command, make sure that the output has
	 * knowledge of the desired variable name.
	 * 
	 * @param head
	 *            InstructionNode at head of tree
	 * @param args
	 *            String of arguments for instruction
	 */
	private void variableCheck(InstructionNode head, List<String> args) {
		if (head.getMyClassification().equals("MakeVariable")) {
			args.set(0, head.getMyChildren().get(0).getMyCommand());
		}
	}

	/**
	 * Create the correct return value from the run value of the head node
	 * 
	 * @param head
	 *            The node to pull the run value from
	 * @return The run value as a double
	 */
	private double parseReturn(InstructionNode head) {
		try {
			return Double.parseDouble(head.getMyRunValue());
		} catch (NumberFormatException e) {
			if (myClass.isValid(head.getMyCommand(), myData)) {
				return 0.0;
			} else {
				throw new InvalidCommandException(RESOURCE_INVALID_COMMAND_NAME);
			}
		}
	}

	/**
	 * Iterate through all of the children of the head node and: A) Execute the
	 * child (if it has not run) and add that run value to the arguments list B)
	 * Grab the run value (if it has already run) and add that value to the
	 * arguments list
	 * 
	 * Following this, check if the head node has already been 'executed.' If
	 * so, add its run value to the end of the list of arguments. Note: This is
	 * an extra feature. If one were to be unaware of its existence, the
	 * functionality could be totally ignored.
	 * 
	 * @param head
	 *            The InstructionNode from which to build a list of arguments
	 * @return The List of arguments as Strings
	 */
	private List<String> buildArguments(InstructionNode head) {
		ArrayList<String> args = new ArrayList<String>();

		for (InstructionNode child : head.getMyChildren()) {
			if (!child.hasRun()) {
				execute(child);
			}
			args.add(child.getMyRunValue());
		}
		if (head.hasRun()) { // Add the head value if the whole thing has
								// already run
			args.add(head.getMyRunValue());
		}
		return args;
	}

	/**
	 * Check to make sure that this node has the correct number of child nodes
	 * 
	 * @param head
	 *            The node to check
	 */
	private void checkChildren(InstructionNode head) {
		int numberNonNullChildren = 0;
		for (InstructionNode child : head.getMyChildren()) {
			if (child != null)
				numberNonNullChildren++;
		}
		if (numberNonNullChildren != ArgumentReaderUtil.getNumArgs(head.getMyClassification(), head.getMyCommand(),
				myData)) {
			throw new WrongArgumentNumberException(RESOURCE_ARGUMENT_NAME);
		}
	}

	/**
	 * Main avenue of reflection
	 * 
	 * @param command
	 *            String representing instruction (e.g., fd)
	 * @param data
	 *            InstructionData holding workspace information
	 * @param args
	 *            The arguments used to make the instruction
	 * 
	 * @return Instruction object corresponding to String
	 */
	public Instruction generateInstruction(String comm, InstructionData data, List<String> args) {
		String classification = myClass.getInstructionType(comm, data);
		String classPath;
		classPath = myClass.findAddressKey(classification);
		InstructionFactory builder = new InstructionFactory(classPath);
		return builder.make(classification, data, args, comm);
	}
}