package instruction.TurtleCommands;

import java.util.List;

import instruction.InstructionData;

/**
 * 
 * @author jimmy
 *
 */
public class ShowTurtle extends TurtleCommand
{

	public ShowTurtle(InstructionData instructionData, List<String> args, String myText)
	{
		super(instructionData, args, myText);
	}

	@Override
	public double execute()
	{
		this.getActiveActor().getImage().setVisible(true);
		return 1;
	}
}
