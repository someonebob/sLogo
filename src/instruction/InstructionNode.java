package instruction;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class represents the nodes used in the parsed string
 * tree. This tree will hold the nodes (each representing its own
 * individual word) in an order conveying the argument relationship
 * between subsequent nodes.
 * 
 * @author maddiebriere
 *
 */

public class InstructionNode {
	private String myText;
	private String myValue;
	private List<InstructionNode> myChildren;
	
	public InstructionNode(String text){
		if(text.isEmpty()){
			//TODO: Error checking
		}
		myText = text;
		//myChildren = constructNodes(text);
	}
	
	/*private List<InstructionNode> constructNodes(String text){
		List<String> myWords = splitString(text);
		myValue = myWords.get(0);
		//TODO: Complete
	}*/
	
	/**
	 * Split String by whitespace to get relevant words
	 * 
	 * @param toParse
	 * @return
	 */
	private List<String> splitString(String toParse){
		ArrayList<String> toRet = new ArrayList<String>();
		Scanner scan = new Scanner(toParse);
		while(scan.hasNext()){
			toRet.add(scan.next());
		}
		return toRet;
	}

	public String getMyText() {
		return myText;
	}
	
	/**
	 * @return The word contained in this node (e.g., 50, fd, back)
	 */
	public String getMyValue(){
		return myValue;
	}
	
	public List<InstructionNode> getMyChildren() {
		return myChildren;
	}
	
	
}
