package interpreter;

import java.util.ArrayList;
import java.util.List;

import util.ArgumentReader;
/**
 * Builds a tree of InstructionNodes for
 * use in execution of an Instruction
 * 
 * @author maddiebriere
 *
 */

public class TreeBuilder {
	
	private String currentText;
	private InstructionClassifier classifier;
	private List <InstructionNode> nodes;
	
	public TreeBuilder(String text, InstructionClassifier c){
		currentText = text;
		classifier = c;
		if(!(text.isEmpty())&& !(c == null))
			nodes = InstructionSplitter.getInstructions(text, c);
		else
			nodes = new ArrayList<InstructionNode>();
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
		//TODO: Fix currenttext bug
		if(getCurrentText().isEmpty()){
			return null;
		}
		InstructionNode head = nodes.remove(0); //take node out of list to add to tree
		String value = InstructionSplitter.getInstructionStrings(getCurrentText()).get(0);
		head.setMyValue(value);
		setCurrentText(InstructionSplitter.removeFirstItem(getCurrentText()));//remove node from current text
		int numArgs = ArgumentReader.getNumArgs(classifier.findShortcutKey(value));
		for(int i=0; i<numArgs; i++){
			head.getMyChildren().add(buildSubTree());
		}
		return head;
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
}
