package instruction.BasicSyntax;

import java.util.List;

import instruction.InstructionData;
import interpreter.Interpreter;

/**
 * Syntax initiated by the "(" symbol,
 * represents multiple inputs for a single command. 
 * Similar to ListStart, but actually has an implemented
 * execute method.
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
	
	private double runValueParse() {
		Interpreter listInterpreter = new Interpreter(getInstructionData());
		return listInterpreter.parseAndRun(getArgumentString(0));
	}

}
