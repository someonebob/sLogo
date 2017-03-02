package instruction.MathOperations;

import java.util.List;

import instruction.Instruction;
import instruction.InstructionData;

/**
 * Superclass of all math instructions. Subclasses' execute()
 * will throw a MathException if so necessary. I wrote all subclasses.
 * @author Matthew Barbano
 *
 */
public abstract class MathOperation extends Instruction {
	
	public MathOperation(InstructionData data,  List<String> args, String myText) {
		super(data, args, myText);
	}
}
