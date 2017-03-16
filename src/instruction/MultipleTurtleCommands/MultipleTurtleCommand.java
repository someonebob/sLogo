package instruction.MultipleTurtleCommands;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import exceptions.NonsensicalArgumentException;
import instruction.Instruction;
import instruction.InstructionData;
import view.TurtleView;

/**
 * <p>
 * Superclass of all Instructions handling multiple turtles. It is assumed that
 * instructionData and args are not null, and that args contains the correct
 * number of non-null entries for each Instruction subclass. Dependencies are
 * InstructionData, List, String, and Instruction's 3-argument constructor.
 * </p>
 * 
 * <p>
 * Note that the following decisions were made about multiple turtle commands,
 * which could vary between SLogo implementations: <br>
 * 1) In "Tell", all turtles must be listed explicitly. If a single number is
 * input, it will not automatically set all turtles from 1 to that number to
 * "told". See "Tell" class documentation for more information. Examples:
 * 
 * <pre>
 * {@code           //assume turtles 0, 1, and 2 initially exist
 * tell [ 0 1 2 ]  //sets turtles 0, 1, and 2 to told
 * tell [ 2 ]      //sets only turtle 2 to told
 * tell [ 3 4 ]    //creates new turtles 3 and 4
 * tell [ 10 ] //throws NonsensicalArgumentException 
 * }
 * </pre>
 * 
 * <br>
 * 2) All indexed Lists - of colors, turtle graphics, and Actors - are
 * 0-indexed, and SLogo command arguments should reflect that. This is to make
 * the SLogo language more naturally match Java's List system of indexing and
 * make it easier for future programmers to extend this project. <br>
 * 3) When an Instruction implementing the ActorSpecificCommand interface
 * contains an argument which is a nested Instruction that does not, the nested
 * Instruction is evaluated only once. For example, in the following code, all
 * turtles move forward the same distance.
 * 
 * <pre>
 * {@code
 * tell [ 0 1 2 3 ]
 * set :x 10
 * fd set :x + :x 10
 * }
 * </pre>
 * 
 * 4) When multiple ActorSpecificCommands are executed and multiple actors are
 * "told", each command is executed for all "told" actors before the next is
 * begun. For example, in the following code, all turtles move forward first
 * before any turn left.
 * 
 * <pre>
 * {@code
 * tell [ 0 1 2 3 ]
 * fd 100
 * left 90
 * }
 * </pre>
 * </p>
 * 
 * @author Matthew Barbano
 *
 */
public abstract class MultipleTurtleCommand extends Instruction {
	private static final String RESOURCE_INVALID_ID_NAME = "InvalidIDMessage";
	private static final String RESOURCE_NONCONSECUTIVE_ID_NAME = "NonconsecutiveIDMessage";

	/**
	 * Standard 3-argument constructor for the Instruction hierarchy. Through a
	 * series of super() constructor calls up the hierarchy, sets 3
	 * corresponding variables in Instruction. No assumptions cause direct
	 * impact in this constructor. Setting any arguments or entries in args to
	 * null will cause errors elsewhere. Design decision: Making args a List
	 * accommodates SLogo commands with different numbers of arguments.
	 * 
	 * @param instructionData
	 *            for accessing frontend data
	 * @param args
	 *            for storing arguments to SLogo commands
	 * @param myText
	 *            a String representation of this Instruction
	 */
	public MultipleTurtleCommand(InstructionData data, List<String> args, String myText) {
		super(data, args, myText);
	}

	/**
	 * Splits the String parameter toConvert by whitespace (any type - space,
	 * tab, newline, etc.) and returns it as an ArrayList of Integers, sorted in
	 * increasing order. Assumes that toConvert contains whitespace separated
	 * nonnegative integers; if not, throws a NonsensicalArgumentException.
	 * Dependencies include String, List, Integer, Collections, and
	 * NonsensicalArgumentException.
	 * 
	 * @param toConvert
	 *            to be parsed
	 * @return ArrayList of integers from parsed String
	 * @throws Nonsensical
	 *             Argument Exception if result of toConvert.split("\\s+") has
	 *             anything other than nonnegative integer entries
	 */
	protected List<Integer> convertStringToIntegerList(String toConvert) {
		String[] idsAsStrings = toConvert.split("\\s+");
		List<Integer> idsAsInts = new ArrayList<>();
		for (String idString : idsAsStrings) {
			try {
				int candidateInt = Integer.parseInt(idString);
				if (candidateInt < 0) {
					throw new NonsensicalArgumentException(RESOURCE_INVALID_ID_NAME);
				} else {
					idsAsInts.add(candidateInt);
				}
			} catch (NumberFormatException exception) {
				throw new NonsensicalArgumentException(RESOURCE_INVALID_ID_NAME);
			}
		}
		Collections.sort(idsAsInts);
		return idsAsInts;
	}

	/**
	 * For all existing actors, sets all those with ids corresponding to the
	 * entries in idsAsInts to "told" and all others to "untold". If idsAsInts
	 * contains any ids beyond those of currently existing actors, corresponding
	 * new actors are created. Assumes that idsAsInts is sorted in ascending
	 * order and that any ids equal to or beyond the initial length of the List
	 * of Actors are consecutively increasing integers. Dependencies include
	 * TurtleView, InstructionData, List, Integer, and
	 * NonsensicalArgumentException.
	 * 
	 * @param idsAsInts
	 */
	protected void handleTolds(Collection<Integer> idsAsInts) {
		List<TurtleView> actorList = getInstructionData().getActorList();
		getInstructionData().setToldAndUntellRest(new ArrayList<>());
		getInstructionData().setToldAndUntellRest(idsAsInts);
		for (Integer id : idsAsInts) {
			if (id == actorList.size()) {
				getInstructionData().newActor();
			} else if (id > actorList.size()) {
				throw new NonsensicalArgumentException(RESOURCE_NONCONSECUTIVE_ID_NAME);
			}
		}
	}
}