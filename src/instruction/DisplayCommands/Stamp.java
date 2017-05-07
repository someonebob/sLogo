package instruction.DisplayCommands;

import java.util.List;

import instruction.InstructionData;

/**
 * Add a stamp to the screen to graphically depict the turtle the moment this
 * command was run
 * 
 * @author jimmy
 *
 */
public class Stamp extends DisplayCommand
{

	public Stamp(InstructionData instructionData, List<String> args, String myText)
	{
		super(instructionData, args, myText);
	}

	@Override
	public double execute()
	{
		this.getInstructionData().newStamp();
		GetShape shape = new GetShape(super.getInstructionData(), super.getArgumentsString(), super.getMyText());
		return shape.execute();
	}

}
