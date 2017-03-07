package interpreter.builders;
import java.util.List;

import instruction.InstructionData;
import interpreter.clean.InstructionSplitter;
import interpreter.misc.InstructionNode;
/**
 * This is a class dedicated to the creation of 
 * Lists and Groups, which cannot be execute and must be handled differently.
 * 
 * @author maddiebriere
 *
 */
public class ListStartUtil extends BuilderUtil {
	private final static String END = "ListEnd";
	private final static String START = "ListStart";
	
	public ListStartUtil(List<InstructionNode> nodes,
			InstructionNode head, String current, InstructionData data){
		super(nodes, head, current, data);
	}
	
	public String construct() {
		String value = "";
		String current = getCurrent();
		while(!getNodes().isEmpty())
		{
			String name = getNodes().get(0).getMyClassification();
			current = InstructionSplitter.removeFirstItem(current);
			if(name.equals(END)){
				break;
			}
			value += getNodes().remove(0).getMyCommand() + " ";
		}
		value = removeSpace(value);
		getHead().setMyRunValue(value);
		getNodes().remove(0); //remove final bracket (closing)
		getHead().setExecutable(false);
		return current;
	}
	
	private static String removeSpace(String value){
		if(!value.isEmpty()){
			value = value.substring(0, value.length()-1);
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