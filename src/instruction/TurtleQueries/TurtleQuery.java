package instruction.TurtleQueries;

import java.util.List;

import instruction.ActorSpecificInstruction;
import instruction.Instruction;
import instruction.InstructionData;

/**
 * Superclass of all turtle query Instructions, which return information about
 * the state of the active actor. This superclass implements
 * ActorSpecificInstruction since all concrete subclasses apply only to specific
 * actor. It is assumed that instructionData and args are not null, and that
 * args contains the correct number of non-null entries for each Instruction
 * subclass. Dependencies are InstructionData, List, String, and Instruction's
 * 3-argument constructor.
 * 
 * @author Matthew Barbano
 *
 */
public abstract class TurtleQuery extends Instruction implements ActorSpecificInstruction {

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
	public TurtleQuery(InstructionData instructionData, List<String> args, String myText) {
		super(instructionData, args, myText);
	}
}
