package instruction.BooleanOperations;

import instruction.InstructionData;
import util.MathUtil;

public class Less extends BooleanOperation{
	
	public Less(InstructionData data, InstructionNode node){
		super(data, node);
	}
	
	@Override
	public double execute() {
		return MathUtil.doubleLessThan(getArgumentDouble(0), getArgumentDouble(1)) ? 1 : 0;
	}

}