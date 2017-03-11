package interpreter.builders;
import java.util.ArrayList;
import java.util.List;
import instruction.InstructionData;
import interpreter.factories.BuilderUtilFactory;
import interpreter.factories.InstructionClassifier;
import interpreter.misc.InstructionNode;
import interpreter.misc.InstructionTracker;
import interpreter.util.ArgumentReaderUtil;
import util.Pair;

public class TreeBuilder {
	
	private InstructionTracker track;
	
	public TreeBuilder(String text, InstructionClassifier clzz, InstructionData data){
		track = new InstructionTracker(text, data, clzz);
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
	public List<InstructionNode> buildFullTree(){
		if(track.getCurrentText().isEmpty()){
			return new ArrayList<InstructionNode>();
		}
		ArrayList<InstructionNode> headNodes = new ArrayList<InstructionNode>();
		while(!track.isEmpty()){
			InstructionNode newHead = buildSubTree();
			if(newHead!=null)
				headNodes.add(newHead); //build a list of nodes from text
		}
		return headNodes;
	}
	
	/**
	 * This builds a tree of InstructioNodes given a list of Instructions 
	 * (The utility of having Instruction type is to have access
	 * to the number of arguments needed for an instruction). It will only build
	 * enough of the tree to satisfied the head node instruction. 
	 * 
	 * Following this, it will return the node it created and the modified text.
	 * 
	 * @param toParse String to parse into instructions for tree constructions
	 * @param clzz InstructionClassifier used to split
	 * @return A Pair with:
	 * A) The new InstructionNode (representing the head instruction)
	 * B) The updated current text
	 */
	public Pair<InstructionNode, String> buildTree(){
		if(track.isEmpty()){
			return new Pair<InstructionNode, String>(null, "");
		}
		InstructionNode newHead = buildSubTree();
		return new Pair<InstructionNode, String>(newHead, track.getCurrentText());
	}
	
	/**
	 * This is the recursive function called upon when an object of this class
	 * builds a tree. This function will iterate through the current nodes and
	 * text (what is left unprocessed from the instruction) and connect the nodes
	 * and remove the processed words from the current text. It will either perform 
	 * this process generically, if there is no BuilderUtil class associated with the
	 * instruction type of the head node, or perform specialized processing (groups,
	 * lists, user-defined commands).
	 * 
	 * This method may terminate before the instruction is entirely processed -- 
	 * if the head node is "satisfied" (gets all of its arguments), this method will 
	 * complete its connecting process and return the head node (now connected
	 * to its children).
	 * 
	 * @return a new InstructionNode representing this individual subtree (there can be 
	 * multiple subtrees in a single instruction). 
	 */
	private InstructionNode buildSubTree(){
		if(track.getCurrentText().isEmpty()){
			return null;
		}
		
		InstructionNode head = track.removeHeadNode(); //take node out of list to add to tree
		String headText = track.getHeadNodeText(); 
		track.setHeadText(head, headText); 
		track.decrementCurrentText(); //remove a node
		
		BuilderUtil build = BuilderUtilFactory.make(head, head.getMyClassification(), track);
		if(build!=null){
			track.setCurrentText(build.construct()); //change current text according to builder
		}
		else{
			int numArgs = ArgumentReaderUtil.getNumArgs(head.getMyClassification(), 
					headText, track.getData());
			buildChildren(numArgs, head);
		}
		return head;
	}
	
	/**
	 * The recursive part of tree-building. This method iterates through all
	 * of the children of a headnode and creates sub-trees for those nodes.
	 * @param numArgs The number of arguments this particular instruction 
	 * (the head instruction) takes
	 * @param head The current head node (just removed from the list)
	 */
	private void buildChildren(int numArgs, InstructionNode head){
		for(int i=0; i<numArgs; i++){
			head.getMyChildren().add(buildSubTree());
		}
	}
	
	
}