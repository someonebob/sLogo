package instruction.BasicSyntax;


import java.util.List;

import instruction.InstructionData;

/**
 * Represents character starting a List of commands
 * This is considered a command type with the type '['
 * and with a value represented by an entire chunk of text
 * (e.g., fd 50 fd 50 ) that can be parsed and executed
 * 
 * @author maddiebriere
 *
 */

public class ListStart extends BasicSyntax {


	public ListStart(InstructionData data,  List<String> args, String myText){
		super(data, args, myText);
	}
	
	//Does nothing -- don't want to execute when made
	@Override
	public double execute() {
		return 0;
	}

}
