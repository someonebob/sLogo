package instruction.TurtleCommands;

import java.util.List;

import instruction.InstructionData;
import view.TurtleView;

/**
 * 
 * @author jimmy
 *
 */
public class PenDown extends TurtleCommand
{

	public PenDown(InstructionData instructionData, List<String> args, String myText)
	{
		super(instructionData, args, myText);
	}

	@Override
	public double execute()
	{
		if (this.getActiveActor() instanceof TurtleView) {
			this.getActiveActor().getPen().penDown();
		}
		return 1;
	}
}
