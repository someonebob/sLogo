package interpreter.builders;

import interpreter.misc.InstructionNode;
import interpreter.misc.InstructionTracker;

/**
 * This is a class dedicated to the creation of Lists, which cannot be execute
 * and must be handled differently. Lists are only executed when they are called
 * upon by other commands (such as conditionals).
 * 
 * N.B: The class' construct method, unlike that of GroupStartUtil, will not
 * produce a child holding the run value. This is because we DO NOT want the
 * List to execute. Instead, we store the List information as a run value.
 * 
 * In order to stop the list from executing, we must to give it (the head node,
 * of type ListStart) some run value (this is the signal in InstructionNode that
 * the node has already been executed). We hence set the head node's value to
 * this newly "parsed" text -- this text will be added to the list of arguments
 * and can be called upon and parsed by conditionals and other commands.
 * 
 * @author maddiebriere
 *
 */
public class ListStartUtil extends BuilderUtil {
	private final static String END = "ListEnd";
	private final static String START = "ListStart";

	public ListStartUtil(InstructionNode head, InstructionTracker track) {
		super(head, track);
	}

	/**
	 * Works with the same commands as in List brackets [ ], but assigns the
	 * entire chunk of text as the run value for the ListStart node. This makes
	 * it so that commands using Lists can create their argument list by calling
	 * upon the runValue of the ListStart node (rather than trying to execute an
	 * invalid command by passing the entire chunk of text to the execute
	 * function).
	 * 
	 * @return String representing the current text (after the list has been
	 *         accounted for and removed from the instruction)
	 */
	public String construct() {
		String value = buildInnerText();
		value = removeSpace(value);
		getHead().setMyRunValue(value);
		return getTrack().getCurrentText();
	}

	/**
	 * Iterate through the text/nodes and build a value representing the text
	 * within the correct bracketing. In other words, grab all of the text
	 * within the brackets, including embedded lists, and return this value.
	 * 
	 * NOTE: This method modifies the list of nodes and the current text
	 * representing the instruction
	 * 
	 * @return The inner text of the list
	 */
	private String buildInnerText() {
		String value = "";
		int innerCount = 0;
		while (!getTrack().isEmpty()) {
			InstructionNode next = getTrack().removeNext();
			String name = next.getMyClassification();
			getTrack().decrementCurrentText();

			if (name.equals(START)) {
				innerCount++;
			}
			if (name.equals(END) && innerCount == 0) {
				break;
			} else if (name.equals(END)) {
				innerCount--;
			}

			value += next.getMyCommand() + " ";
		}
		return value;
	}

	private static String removeSpace(String value) {
		if (!value.isEmpty()) {
			value = value.substring(0, value.length() - 1);
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