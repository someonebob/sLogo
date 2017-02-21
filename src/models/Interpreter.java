package models;

import instruction.InstructionData;

/**	This interface will use a variety of resource files to transform
	An input string into a useable command (we call it an Instruction).
	By returning an Instruction, we have transformed something with no functionality
	Into something capable of executing and returning knowledge about itself.
	
	@maddiebriere
**/

public interface Interpreter{
	/**
	Parse the instruction from the input String and then execute the instruction
	 **/
	public void parseAndRun(String s, InstructionData i);
}
