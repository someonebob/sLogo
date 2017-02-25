package instruction.TurtleQueries;

import instruction.InstructionData;
import interpreter.InstructionNode;

public class XCoordinate extends TurtleQuery{
	
	public XCoordinate(){
		super(new InstructionData(), new InstructionNode());
	}
	public XCoordinate(InstructionData instructionData, InstructionNode root) {
		super(instructionData, root);
	}

	@Override
	public double execute() {
		return getActiveActor().getLocation().getX();
	}
}
