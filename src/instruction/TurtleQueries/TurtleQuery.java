package instruction.TurtleQueries;

import java.util.List;

import instruction.Instruction;
import instruction.InstructionData;

public abstract class TurtleQuery extends Instruction{
	
	public TurtleQuery(InstructionData instructionData, List<String> args){
		super(instructionData, args);
	}
}
