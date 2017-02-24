package instruction.TurtleQueries;

import instruction.Instruction;
import instruction.InstructionData;
import interpreter.InstructionNode;

public abstract class TurtleQuery extends Instruction{
	private static int NUM_ARGS = 0;
	
	public TurtleQuery(InstructionData instructionData, InstructionNode root){
		super(instructionData, root);
	}
	
	public int getNumArgs(){
		return NUM_ARGS;
	}
}
