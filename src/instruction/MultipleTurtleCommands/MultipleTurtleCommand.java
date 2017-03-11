package instruction.MultipleTurtleCommands;

import java.util.List;

import instruction.Instruction;
import instruction.InstructionData;

public abstract class MultipleTurtleCommand extends Instruction{

	public MultipleTurtleCommand(InstructionData data, List<String> args, String myText) {
		super(data, args, myText);
	}

}
