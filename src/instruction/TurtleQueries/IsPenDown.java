package instruction.TurtleQueries;

import java.util.List;

import instruction.InstructionData;
import view.TurtleView;

/**
 * 
 * @author jimmy
 *
 */
public class IsPenDown extends TurtleQuery
{

	public IsPenDown(InstructionData instructionData, List<String> args, String myText)
	{
		super(instructionData, args, myText);
	}

	@Override
	public double execute()
	{
		System.out.println("HAHGA");
		if (getActiveActor() instanceof TurtleView) {
			return ((TurtleView) getActiveActor()).getPen().isUp() ? 0 : 1;
		}
		return 0;
	}

}
