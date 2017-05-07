package instruction.DisplayCommands;

import java.util.List;

import instruction.InstructionData;

/**
 * Clear all of the stamps that have been made.
 * 
 * @author jimmy
 *
 */
public class ClearStamps extends DisplayCommand
{

	public ClearStamps(InstructionData instructionData, List<String> args, String myText)
	{
		super(instructionData, args, myText);
	}

	@Override
	public double execute()
	{
		return this.getInstructionData().clearStamps();
	}

}
