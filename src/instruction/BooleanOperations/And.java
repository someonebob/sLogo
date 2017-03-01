package instruction.BooleanOperations;

import java.util.List;

import instruction.InstructionData;
import util.MathUtil;

public class And extends BooleanOperation {
	public And(InstructionData data, List<String> args, String myText) {
		super(data, args, myText);
	}

	@Override
	public double execute() {
		return !MathUtil.doubleEquals(getArgumentDouble(0), 0) && !MathUtil.doubleEquals(getArgumentDouble(1), 0) ? 1
				: 0;
	}
}
