package interpreter.misc;

import java.util.ArrayList;
import java.util.List;

import instruction.InstructionData;
import interpreter.classification.InstructionClassifier;
import interpreter.clean.InstructionSplitter;

/**
 * Used to keep track of the current status of
 * processing within an instruction, including
 * the String of text and the nodes created in
 * the initial sweep.
 * 
 * @author maddiebriere
 *
 */

public class InstructionTracker {

	private List<InstructionNode> nodes;
	private String currentText;
	private InstructionData data;
	private InstructionClassifier classifier;
	
	public InstructionTracker(String current,
			InstructionData data, InstructionClassifier classifier){
		this.currentText = current;
		this.data = data;
		this.classifier = classifier;
		if(!(current.isEmpty())&& !(classifier == null))
			nodes = InstructionSplitter.getInstructions(current, classifier, data);
		else
			nodes = new ArrayList<InstructionNode>();
	}
	
	/**
	 * Count words up to a given stopping phrase (while removing the words) 
	 * and return the number iterated over.
	 * 
	 * @return
	 */
	public List<InstructionNode> countAndRemoveArgs(String END){
		List<InstructionNode> toRet = new ArrayList<InstructionNode>();
		while(!isEmpty()){
			decrementCurrentText();
			if(peekNext().equals(END)){
				removeNext();
				break;
			}
			else{
				toRet.add(removeNext());
			}
		}
		return toRet;
	}
	
	public void decrementCurrentText() {
		setCurrentText(InstructionSplitter.removeFirstItem(getCurrentText()));
	}

	public void setHeadText(InstructionNode head, String value) {
		head.setMyCommand(value);
	}

	public InstructionNode removeHeadNode() {
		return nodes.remove(0);
	}

	public String getHeadNodeText() {
		return InstructionSplitter.getInstructionStrings(getCurrentText()).get(0);
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
	
	public List<InstructionNode> getNodes(){
		return nodes;
	}
	
	public void setNodes(List<InstructionNode> nodes) {
		this.nodes = nodes;
	}
	
	public InstructionNode removeNext() {
		return getNodes().remove(0);
	}

	public InstructionNode peekNext() {
		return getNodes().get(0);
	}

	public boolean isEmpty() {
		return getNodes().isEmpty();
	}


}
