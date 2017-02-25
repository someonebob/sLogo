package instruction.BooleanOperations;

import instruction.Instruction;
import instruction.InstructionData;
import interpreter.InstructionNode;

public class Less extends BooleanOperation{
	
	public Less(){
		super(new InstructionData(), new InstructionNode());
	}
	
	public Less(InstructionData data, InstructionNode node){
		super(data, node);
	}
	
	@Override
	public double execute() {
		return (getArgument(0) < getArgument(1)) ? 1 : 0;
	}

}
