package instruction.MultipleTurtleCommands;

import java.util.List;

import instruction.ActorSpecificInstruction;
import instruction.InstructionData;
import util.MathUtil;

public class ID extends MultipleTurtleCommand implements ActorSpecificInstruction {
	
	public ID(InstructionData data, List<String> args, String myText) {
		super(data, args, myText);
	}

	@Override
	public double execute() {
		return getActiveActor().getID().getID();
	}
}