package instruction.DisplayCommands;

import java.util.List;

import exceptions.InvalidIndexException;
import instruction.Instruction;
import instruction.InstructionData;
import util.MathUtil;

public abstract class DisplayCommand extends Instruction{
	public DisplayCommand(InstructionData instructionData,  List<String> args, String myText) {
		super(instructionData, args, myText);
	}
}
