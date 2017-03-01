package instruction;

import java.util.ArrayList;
import java.util.List;

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

	public Instruction(InstructionData instructionData, List<String> args)
	{
		this.instructionData = instructionData;
		this.arguments = args;
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

	/**
	 * Performs the action associated with this Instruction. Unique for each
	 * concrete subclass implementation. Returns the same return value for the
	 * SLogo command represented by this instruction.
	 */
	public abstract double execute();

	protected List<Double> getArgumentsDouble()
	{
		List<Double> doubleList = new ArrayList<>();
		for (String stringArgument : arguments) {
			doubleList.add(Double.parseDouble(stringArgument));
		}
		return doubleList;
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
}
