package instruction.TurtleQueries;

import instruction.InstructionData;
import interpreter.InstructionNode;

public class YCoordinate extends TurtleQuery{
	
	public YCoordinate(){
		super(new InstructionData(), new InstructionNode());
	}
	public YCoordinate(InstructionData instructionData, InstructionNode root) {
		super(instructionData, root);
	}

	@Override
	public double execute() {
		return getActiveActor().getLocation().getY();
	}
	
}
