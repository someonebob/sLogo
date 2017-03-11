package instruction.MultipleTurtleCommands;

import java.util.List;

import instruction.InstructionData;

/**
 * Two versions of Tell exist:
 * 1) tells all turtles up to and including the argument
 * 2) tells only those specifically specified by the argument
 * The default is version (2). To use version (1), I will implement another
 * Instruction, TellUpTo.
 * @author Matthew Barbano
 *
 */
public class Tell extends MultipleTurtleCommand {
	public Tell(InstructionData data, List<String> args, String myText) {
		super(data, args, myText);
	}
	
	//TODO Change implementation so actorList can hold ids in any order
	//Example: currently 3 actors, then do (0-indexing) Tell [ 0 1 2 7 10 ] - the 7 and 10
	@Override
	public double execute() {
		//Get list argument as string of numbers (ex: "1 2 3") and convert to array
		//System.out.println("Arg string: " + getArgumentString(0));
		List<Integer> idsAsInts = convertStringToIntegerList(getArgumentString(0));
		handleTolds(idsAsInts);
		return idsAsInts.get(idsAsInts.size() - 1);
	}
}
