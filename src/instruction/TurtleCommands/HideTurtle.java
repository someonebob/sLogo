package instruction.TurtleCommands;

import java.util.List;

import instruction.InstructionData;

/**
 * 
 * @author jimmy
 *
 */
public class HideTurtle extends TurtleCommand
{

	public HideTurtle(InstructionData instructionData, List<String> args, String myText)
	{
		super(instructionData, args, myText);
	}

	@Override
	public double execute()
	{
		this.getActiveTurtle().getImageView().setVisible(false);
		return 0;
	}
}
