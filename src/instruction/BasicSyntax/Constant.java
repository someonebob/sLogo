package instruction.BasicSyntax;

import java.util.List;

import instruction.Instruction;
import instruction.InstructionData;

/**
 * Instruction representing a constant. Its execute() 
 * returns the double value of the constant.
 * @author Matthew Barbano
 *
 */
public class Constant extends Instruction {

	public Constant(InstructionData instructionData, List<String> args, String myText) {
		super(instructionData, args, myText);
	}

	@Override
	public double execute() {
		return Double.parseDouble(getMyText());
	}

}
