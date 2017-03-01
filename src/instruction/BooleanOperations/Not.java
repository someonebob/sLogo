package instruction.BooleanOperations;

import java.util.List;

import instruction.InstructionData;
import util.MathUtil;

public class Not extends BooleanOperation{
	public Not(InstructionData data,  List<String> args, String myText){
		super(data, args, myText);
	}
	@Override
	public double execute() {
		return MathUtil.doubleEquals(getArgumentDouble(0), 0) ? 1 : 0;
	}
}
