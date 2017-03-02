package instruction.TurtleCommands;

import java.util.List;

import instruction.InstructionData;
import view.TurtleView;

/**
 * 
 * @author jimmy
 *
 */
public class PenUp extends TurtleCommand
{

	public PenUp(InstructionData instructionData, List<String> args, String myText)
	{
		super(instructionData, args, myText);
	}

	@Override
	public double execute()
	{
		if (this.getActiveActor() instanceof TurtleView) {
			((TurtleView) this.getActiveActor()).getPen().penUp();
		}
		return 0;
	}
}
