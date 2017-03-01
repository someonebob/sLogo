package instruction.BooleanOperations;

import java.util.List;

import instruction.InstructionData;
import util.MathUtility;

public class Less extends BooleanOperation{
	
	public Less(InstructionData data,  List<String> args){
		super(data, args);
	}
	
	@Override
	public double execute() {
		return MathUtility.doubleLessThan(getArgumentDouble(0), getArgumentDouble(1)) ? 1 : 0;
	}

}
