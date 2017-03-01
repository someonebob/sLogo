package instruction.BooleanOperations;

import java.util.List;

import instruction.InstructionData;
import util.MathUtility;

public class Not extends BooleanOperation{
	public Not(InstructionData data,  List<String> args){
		super(data, args);
	}
	@Override
	public double execute() {
		return MathUtility.doubleEquals(getArgumentDouble(0), 0) ? 1 : 0;
	}
}
