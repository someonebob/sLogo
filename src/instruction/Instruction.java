package instruction;

import java.util.ArrayList;
import java.util.List;

import interpreter.InstructionNode;

/**
 * This is a the root of the inheritance hierarchy whose concrete subclasses
 * represent all possible commands in this project's implementation of the SLogo
 * language. Of greatest importance is its execute() method, which performs the
 * unique action associated with any given Instruction.
 * 
 * @author Matthew Barbano
 *
 */
public abstract class Instruction {
	private InstructionData instructionData;
	private InstructionNode root;
	private String textRepresentation;
	
	
	public Instruction(){
		instructionData = new InstructionData();
		root = new InstructionNode();
		textRepresentation = "";
	}
	
	public Instruction(InstructionData instructionData, InstructionNode root){
		this.instructionData = instructionData;
		this.textRepresentation = root.getMyText();  //Returns String for FULL command
		this.root = root;
	}
	
	/**
	 * Returns the instructionData object associated with this object
	 * 
	 * @return
	 */
	public InstructionData getInstructionData(){
		return instructionData;
	}

	/**
	 * Sets the instructionData object associated with this object to
	 * instructionData
	 * 
	 * @param instructionData
	 */
	public void setInstructionData(InstructionData instructionData){
		this.instructionData = instructionData;
	}

	/**
	 * Performs the action associated with this Instruction. Unique for each
	 * concrete subclass implementation. Returns the same return value for
	 * the SLogo command represented by this instruction.
	 */
	public abstract double execute();

	/**
	 * Returns the text originally input for an instruction, such as "Forward
	 * 50." It is used by the LogoController to display previously entered
	 * commands. It can be used by future programmers if parsing a command's
	 * text in a new Instruction becomes necessary.
	 **/
	public String getText(){
		return textRepresentation;
	}
	
	public abstract int getNumArgs();  //Implement as private constant in subclasses
	
	public InstructionNode getRoot(){
		return root;
	}
	
	protected List<Double> getArguments(){
		List<Double> arguments = new ArrayList<>();
		for(InstructionNode child : getRoot().getMyChildren()){
			arguments.add(Double.parseDouble(child.getMyValue()));
		}
		return arguments;
	}
}
