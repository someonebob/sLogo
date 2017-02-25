package instruction.BooleanOperations;

import instruction.InstructionData;
import interpreter.InstructionNode;

public class Not extends BooleanOperation{
	private static final int NUM_ARGS = 1;
	public Not() {
		super(new InstructionData(), new InstructionNode());
	}
	public Not(InstructionData data, InstructionNode node){
		super(data, node);
	}
	@Override
	public double execute() {
		return getArgument(0) == 0 ? 1 : 0;
	}
	public int getNumArgs(){
		return NUM_ARGS;
	}
}
