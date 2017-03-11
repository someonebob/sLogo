package interpreter.builders;

import interpreter.factories.GroupTypeFactory;
import interpreter.grouptypes.GroupType;
import interpreter.misc.InstructionNode;
import interpreter.misc.InstructionTracker;
import interpreter.util.ArgumentReaderUtil;

/**
 * Subclass of BuilderUtil that processes the current
 * text and nodes when the parser stumbles upon a GroupStart
 * command. This class processes up to the end of the Grouping (the
 * GroupEnd) and returns the nodes and current text with those sections
 * removed, and the processed instructions inside the grouping
 * saved within the head node.
 * 
 * @author maddiebriere
 *
 */

public class GroupStartUtil extends BuilderUtil{
	private final static String END = "GroupEnd";
	private final static String START = "GroupStart";
	private final static String LAYER = "Layer";
	
	public GroupStartUtil(InstructionNode head, InstructionTracker track){
		super(head, track);
	}
	
	/**
	 * Work with the same commands as in Group brackets ( ), but
	 * rearrange it so that the initial TreeBuilder can interpret it. Does not
	 * parse the text -- only rearranges for parsing in the main TreeBuilder. It
	 * stores the altered text (from with in the brackets)
	 *  in a child node of the GroupStart head node, for 
	 * parsing (called in the GroupStart instruction class). 
	 * 
	 * @return The String representing the new current text (re-ordered)
	 */
	public String construct() {
		InstructionNode next = getTrack().removeNext();
		getTrack().decrementCurrentText();
		
		String instruction = next.getMyCommand();
		String type = next.getMyClassification();
		int numArgs = ArgumentReaderUtil.getNumArgs(next, getTrack().getData());
		
		GroupTypeFactory build = new GroupTypeFactory(type);
		GroupType execute = build.make(getTrack(), instruction, numArgs);
		addChild(execute.rearrangeWords());
		
		return getTrack().getCurrentText();
	}
	
	public static String getStartBracket() {
		return START;
	}

	public static String getEndBracket() {
		return END;
	}

}
