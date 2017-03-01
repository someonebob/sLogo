package instruction.CoreInstructions;

import instruction.Instruction;
import instruction.InstructionData;
import interpreter.InstructionNode;
import interpreter.Interpreter;

public abstract class CoreInstruction extends Instruction {
	
	public CoreInstruction(InstructionData instructionData, InstructionNode root) {
		super(instructionData, root);
	}
	
	protected double runListCommands(int argumentNumber) {
		Interpreter listInterpreter = new Interpreter("English");    //Need to change when decide on way to set language
		//Temporary:
		listInterpreter.parseAndRun(getArgumentString(argumentNumber), getInstructionData());
		return 1;
		//Possibly final:
		//return listInterpreter.parseAndRun(getArgumentString(1), getInstructionData());
		//Somehow return the value of last executed command in list - need Maddie to decide how she gives this to me
	}
}
