package instruction.TurtleQueries;

import instruction.Instruction;
import instruction.InstructionData;
import interpreter.InstructionNode;

public abstract class TurtleQuery extends Instruction{
	
	public TurtleQuery(InstructionData instructionData, InstructionNode root){
		super(instructionData, root);
	}
}
