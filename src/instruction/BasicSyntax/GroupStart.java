package instruction.BasicSyntax;

import java.util.List;

import instruction.Instruction;
import instruction.InstructionData;
import interpreter.Interpreter;

/**
 * Syntax initiated by the "(" symbol,
 * represents multiple inputs for a single command
 * 
 * @author maddiebriere
 *
 */

public class GroupStart extends BasicSyntax {

	public GroupStart(InstructionData instructionData, List<String> args, String myText) {
		super(instructionData, args, myText);
	}

	@Override
	public double execute() {
		return runValueParse();
	}
	
	//Repeated TODO: Fix
	private double runValueParse() {
		//TODO Need to change when decide on way to set language (possibly through InstructionData)
		Interpreter listInterpreter = new Interpreter(getInstructionData());
		return listInterpreter.parseAndRun(getArgumentString(0));
	}

}
