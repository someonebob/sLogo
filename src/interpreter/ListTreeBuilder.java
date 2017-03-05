package interpreter;
import java.util.List;
import util.Pair;
/**
 * This is a class dedicated to the creation of 
 * Lists and Groups, which cannot be execute and must be handled differently.
 * 
 * @author maddiebriere
 *
 */
public class ListTreeBuilder {
	private final static String END = "ListEnd";
	private final static String START = "ListStart";
	
	public static String construct(List<InstructionNode> nodes, InstructionNode head, String current) {

		String value = "";
		while(!nodes.isEmpty())
		{
			String name = nodes.get(0).getMyClassification();
			current = InstructionSplitter.removeFirstItem(current);
			if(name.equals(END)){
				break;
			}
			value += nodes.remove(0).getMyCommand() + " ";
		}
		value = removeSpace(value);
		head.setMyRunValue(value);
		nodes.remove(0); //remove final bracket (closing)
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