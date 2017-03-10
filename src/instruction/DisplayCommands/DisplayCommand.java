package instruction.DisplayCommands;

import java.util.List;

import instruction.ActorSpecificInstruction;
import instruction.Instruction;
import instruction.InstructionData;

public abstract class DisplayCommand extends Instruction{
	public DisplayCommand(InstructionData instructionData, List<String> args, String myText) {
		super(instructionData, args, myText);
	}
}