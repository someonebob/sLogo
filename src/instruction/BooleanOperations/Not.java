package instruction.BooleanOperations;

import instruction.InstructionData;
import util.MathUtil;

public class Not extends BooleanOperation{
	public Not(InstructionData data, InstructionNode node){
		super(data, node);
	}
	@Override
	public double execute() {
		return MathUtil.doubleEquals(getArgumentDouble(0), 0) ? 1 : 0;
	}
}