package instruction.BooleanOperations;

import java.util.List;

import instruction.ActorSpecificInstruction;
import instruction.Instruction;
import instruction.InstructionData;

/**
 * Superclass of all boolean operation Instructions. Note that
 * 0 is considered false and any nonzero value is considered true. I wrote
 * all subclasses.
 * @author Matthew Barbano
 *
 */
public abstract class BooleanOperation extends Instruction{
	public BooleanOperation(InstructionData instructionData,  List<String> args, String myText) {
		super(instructionData, args, myText);
	}
}
