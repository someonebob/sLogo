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
	
	public static String buildList(Pair brackets, List<InstructionNode> nodes, InstructionNode head, String current){

		String value = "";
		boolean endHit = false;
		while(!nodes.isEmpty())
		{
			String name = nodes.get(0).getMyClassification();
			String end = brackets.getMyB();
			current = InstructionSplitter.removeFirstItem(current);
			if(name.equals(end)){
				endHit = true;
				break;
			}
			value += nodes.remove(0).getMyValue() + " ";
		}
		value = removeSpace(value);
		if(!endHit){
			//TODO: Error handling
		}
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
}