package interpreter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import instruction.InstructionData;
import util.ArgumentReader;
import util.Pair;
/**
 * Builds a tree of InstructionNodes for
 * use in execution of an Instruction
 * 
 * @author maddiebriere
 *
 */
public class TreeBuilder {
	private static final String LIST_START = "ListStart";
	private static final String LIST_END = "ListEnd";
	private static final String GROUP_START = "GroupStart";
	private static final String GROUP_END = "GroupEnd";
	
	private String currentText;
	private InstructionClassifier classifier;
	private List <InstructionNode> nodes;
	private Set<Pair> myBrackets;
	private InstructionData data;
	
	public TreeBuilder(String text, InstructionClassifier c, InstructionData d){
		currentText = text;
		classifier = c;
		if(!(text.isEmpty())&& !(c == null))
			nodes = InstructionSplitter.getInstructions(text, c);
		else
			nodes = new ArrayList<InstructionNode>();
		populateBrackets();
		data=d;
	}
	
	private void populateBrackets(){
		myBrackets = new HashSet<Pair>();
		myBrackets.add(new Pair(LIST_START, LIST_END));
		myBrackets.add(new Pair(GROUP_START, GROUP_END));
	}
	
	/**
	 * This builds a tree of InstructioNodes given a list of Instructions 
	 * (The utility of having Instruction type is to have access
	 * to the number of arguments needed for an instruction)
	 * 
	 * @param toParse String to parse into instructions for tree constructions
	 * @param clzz InstructionClassifier used to split
	 * @return A list of single InstructionNode, each head of their own tree
	 */
	public List<InstructionNode> buildTree(){
		if(getClassifier() == null){
			//TODO: Error check
		}
		
		if(getCurrentText().isEmpty()){
			return new ArrayList<InstructionNode>();
		}
		
		ArrayList<InstructionNode> headNodes = new ArrayList<InstructionNode>();
		/**
		 * While there is still text left, continue to iterate through tree and build new node
		 */
		while(!getCurrentText().isEmpty()){
			//TODO: Check param
			headNodes.add(buildSubTree());
		}
		return headNodes;
	}
	
	
	private InstructionNode buildSubTree(){
		if(getCurrentText().isEmpty()){
			return null;
		}
		
		InstructionNode head = nodes.remove(0); //take node out of list to add to tree
		String value = InstructionSplitter.getInstructionStrings(getCurrentText()).get(0);
		head.setMyValue(value);
		setCurrentText(InstructionSplitter.removeFirstItem(getCurrentText()));//remove node from current text
		String classification = classifier.findAnyKey(value, data);
		Pair brackets = getBrackets(classification);
		
		if(brackets==null){
			int numArgs = 0; // default
			if(!classification.equals("NO MATCH")){
				//TODO: Remove assumption of constant default
				numArgs = ArgumentReader.getNumArgs(classification, data);
				if(numArgs == -1){
					try{
						numArgs = 0;
						head.setMyValue(data.getVariableValue(value));
					} catch(Exception e){
						//TODO: Error handling
					}
				}
			}
			for(int i=0; i<numArgs; i++){
				head.getMyChildren().add(buildSubTree());
			}
		}
		else{
			String newCurrent = ListTreeBuilder.buildList(brackets, nodes, head, getCurrentText());
			setCurrentText(newCurrent);
		}
		return head;
	}
	
	private Pair getBrackets(String value){
		for(Pair pair: myBrackets){
			if(pair.getMyA().equals(value)){
				return pair;
			}
		}
		return null;
	}
	
	public String getCurrentText() {
		return currentText;
	}
	public void setCurrentText(String currentText) {
		this.currentText = currentText;
	}
	public InstructionClassifier getClassifier() {
		return classifier;
	}
	public void setClassifier(InstructionClassifier classifier) {
		this.classifier = classifier;
	}

	public InstructionData getData() {
		return data;
	}

	public void setData(InstructionData data) {
		this.data = data;
	}
	
	
}