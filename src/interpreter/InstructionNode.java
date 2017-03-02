package interpreter;
import java.util.ArrayList;
import java.util.List;

import exceptions.WrongArgumentNumberException;
/**
 * This class represents the nodes used in the parsed string tree. This tree
 * will hold the nodes (each representing its own individual word) in an order
 * conveying the argument relationship between subsequent nodes.
 * 
 * @author maddiebriere
 *
 */
public class InstructionNode {
	
	private static final String RESOURCE_ARGUMENTS_NAME = "WrongArgumentNumberMessage";
	
	private String myClassification; // The String representing the type (Forward, Equal, Comment)
	private String myValue; //String command
	private List<InstructionNode> myChildren;
	private String myRunValue; //This is used for tree traversal, to check if a command has been excuted already
	private boolean isExecutable; //Used for list/ group creation
	private int properNumArgs = -1;
	
	public InstructionNode() {
		this("", "", new ArrayList<InstructionNode>());
	}
	
	public InstructionNode(String clss, String value) {
		this(clss, value, new ArrayList<InstructionNode>());
	}
	public InstructionNode(String clss, String value, List<InstructionNode> children) {
		if (value.isEmpty()) {
			throw new WrongArgumentNumberException(RESOURCE_ARGUMENTS_NAME);
		}
		myClassification = clss;
		myValue = value;
		myChildren = children;
		myRunValue = "NO RUN"; //default
		isExecutable = true;
	}
	/**
	 * Pieces together a String from the current Node using the current Node
	 * and all of its children.
	 * @return String representing the entire Node
	 */
	public String getMyText() {
		String inst = recursivePrint(this, "");
		return inst.substring(0, inst.length()-1);//Exclude final space
	}
	
	private String recursivePrint(InstructionNode node, String curr){
		curr += node.getMyValue() + " ";
		for(InstructionNode child: node.getMyChildren()){
			curr = recursivePrint(child, curr);
		}
		return curr;
	}
	
	/**
	 * 
	 * 
	 * TODO
	 * 
	 * Pieces together a String from the current Node using the current Node
	 * and all of its NON-EXECUTED children.
	 * @return String representing the entire Node as nonexecuted instructions
	 */
	/**public String getMyRunText() {
		String inst = recursiveRunPrint(this, "");
		return inst.substring(0, inst.length()-1);//Exclude final space
	}
	
	private String recursiveRunPrint(InstructionNode node, String curr){
		if(!node.hasRun())
			curr += node.getMyValue() + " ";
		for(InstructionNode child: node.getMyChildren()){
			curr = recursiveRunPrint(child, curr);
		}
		return curr;
	}*/
	/**
	 * Return only the head value (current node)
	 * 
	 * @return The word contained in this node (e.g., 50, fd, back)
	 */
	public String getMyValue() {
		return myValue;
	}
	/**
	 * Get all of the nodes attached to the current InstructionNode. Allows for
	 * traversal of the InstructionNode tree
	 * 
	 * @return child nodes of the current InstructionNode
	 */
	public List<InstructionNode> getMyChildren() {
		return myChildren;
	}
	public String getMyClassification() {
		return myClassification;
	}
	public void setMyClassification(String myClassification) {
		this.myClassification = myClassification;
	}
	public void setMyValue(String myValue) {
		this.myValue = myValue;
	}
	public void setMyChildren(List<InstructionNode> myChildren) {
		this.myChildren = myChildren;
	}
	public String getMyRunValue() {
		return myRunValue;
	}
	public void setMyRunValue(String myRunValue) {
		this.myRunValue = myRunValue;
	}
	
	/**
	 * Check if this node has been evaluated
	 * @return true if the run value is not equal to NO RUN (the default), which means the
	 * instruction has been run, false otherwise
	 */
	public boolean hasRun(){
		return !myRunValue.equals("NO RUN");
	}

	/**
	 * Retrieve the current run-value (if this node was executed)
	 * OR retrieve a list holding
	 * all of the non-executed InstructionNodes as a String for
	 * later execution
	 * @return
	 */
	/**public String getRunValueList(){
		
	}*/
	
	public void setProperNumArgs(int properNumber){
		properNumArgs = properNumber;
	}
	
	public int getProperNumArgs(){
		return properNumArgs;
	}
	
}