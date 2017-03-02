package instruction.TurtleQueries;

import java.util.List;

import instruction.Instruction;
import instruction.InstructionData;

/**
 * Superclass of all Turtle Query commands. I wrote
 * all subclasses, except PenDown and Showing (jimmy).
 * @author Matthew Barbano
 *
 */
public abstract class TurtleQuery extends Instruction{
	
	public TurtleQuery(InstructionData instructionData, List<String> args, String myText){
		super(instructionData, args, myText);
	}
}
