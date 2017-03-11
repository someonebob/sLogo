package instruction;

import java.util.ArrayList;
import java.util.List;

import interpreter.Interpreter;
import view.ActorView;

/**
 * This is a the root of the inheritance hierarchy whose concrete subclasses
 * represent all possible commands in this project's implementation of the SLogo
 * language. Of greatest importance is its execute() method, which performs the
 * unique action associated with any given Instruction.
 * 
 * @author Matthew Barbano
 *
 */
public abstract class Instruction
{
	private InstructionData instructionData;
	private List<String> arguments;
	private String myText;

	public Instruction(InstructionData instructionData, List<String> args, String myText)
	{
		this.instructionData = instructionData;
		this.arguments = args;
		this.myText = myText;
	}

	/**
	 * Returns the instructionData object associated with this object
	 * 
	 * @return
	 */
	protected InstructionData getInstructionData()
	{
		return instructionData;
	}

	/**
	 * Sets the instructionData object associated with this object to
	 * instructionData
	 * 
	 * @param instructionData
	 */
	protected void setInstructionData(InstructionData instructionData)
	{
		this.instructionData = instructionData;
	}
	
	public double executeAllToldTurtles(){
		int index = 0;
		double returnValue = -1.0;
		//TODO Return values
		
		for(ActorView av : getInstructionData().getActors()){
			System.out.println("Id: " + av.getID().getID() + " Told: " + av.isTold());
		}
		
		if(this instanceof ActorSpecificInstruction){
			for(ActorView actor : instructionData.getActors()){
				instructionData.setActiveActorIndex(index);
				if(actor.isTold()){
					System.out.println("Specific");
					System.out.println("Name: " + myText);
					System.out.println("ID: " + actor.getID().getID());
					for(ActorView av : getInstructionData().getActors()){
						System.out.println("Id: " + av.getID().getID() + " Told: " + av.isTold());
					}
					returnValue = execute();
				}
				index++;
			}
		}
		else{
			System.out.println("Nonspecific");
			System.out.println("Name: " + myText);
			returnValue = execute();
		}
		return returnValue;
	}
	
	/**
	 * Performs the action associated with this Instruction. Unique for each
	 * concrete subclass implementation. Returns the same return value for the
	 * SLogo command represented by this instruction.
	 */
	protected abstract double execute();

	protected List<Double> getArgumentsDouble()
	{
		List<Double> doubleList = new ArrayList<>();
		for (String stringArgument : arguments) {
			doubleList.add(Double.parseDouble(stringArgument));
		}
		return doubleList;
	}
	
	protected double runListCommands(int argumentNumber) {
		//TODO Need to change when decide on way to set language (possibly through InstructionData)
		Interpreter listInterpreter = new Interpreter(getInstructionData());
		//System.out.println(getArgumentString(argumentNumber));
		return listInterpreter.parseAndRun(getArgumentString(argumentNumber));
	}

	protected double getArgumentDouble(int index)
	{
		return Double.parseDouble(arguments.get(index));
	}

	protected List<String> getArgumentsString()
	{
		return arguments;
	}

	protected String getArgumentString(int index)
	{
		return arguments.get(index);
	}

	protected ActorView getActiveActor()
	{
		return getInstructionData().getActiveActor();
	}

	protected String getMyText()
	{
		return myText;
	}
}
