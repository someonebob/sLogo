package instruction;

import java.util.ArrayList;
import java.util.List;

import interpreter.InstructionNode;
import models.Actor;

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
	private List<Double> arguments;
	/*
	public static void main(String[] args){
		InstructionData data = new InstructionData();
		Instruction forward = new Backward(data, new InstructionNode());
		System.out.println(forward.execute());
		System.out.println(data.getActiveActor().getLocation());
	}
	*/
	public Instruction(){
		instructionData = new InstructionData();
		root = new InstructionNode();
		textRepresentation = "";
		arguments = new ArrayList<>();
	}
	
	public Instruction(InstructionData instructionData, InstructionNode root){
		this.instructionData = instructionData;
		this.textRepresentation = root.getMyText();
		this.root = root;
		this.arguments = convertTreeToArguments();
	}
	
	/**
	 * Returns the instructionData object associated with this object
	 * 
	 * @return
	 */
	protected InstructionData getInstructionData(){
		return instructionData;
	}

	/**
	 * Sets the instructionData object associated with this object to
	 * instructionData
	 * 
	 * @param instructionData
	 */
	protected void setInstructionData(InstructionData instructionData){
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
	
	public abstract int getNumArgs();
	
	protected InstructionNode getRoot(){
		return root;
	}
	
	private List<Double> convertTreeToArguments(){
		List<Double> argumentsLocal = new ArrayList<>();
		for(InstructionNode child : getRoot().getMyChildren()){
			argumentsLocal.add(Double.parseDouble(child.getMyValue()));
		}
		return argumentsLocal;
	}
	
	protected List<Double> getArguments(){
		return arguments;
	}
	
	protected double getArgument(int index){
		return arguments.get(index);
	}
	
	protected Actor getActiveActor(){
		return getInstructionData().getActiveActor();
	}
}
