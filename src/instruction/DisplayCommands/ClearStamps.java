package instruction.DisplayCommands;

import java.util.List;

import instruction.ActorSpecificInstruction;
import instruction.InstructionData;
import view.ActorView;
import view.TurtleView;

public class ClearStamps extends DisplayCommand implements ActorSpecificInstruction{

	public ClearStamps(InstructionData instructionData, List<String> args, String myText) {
		super(instructionData, args, myText);
	}

	@Override
	protected double execute() {
		double toRet = 0;
		for(ActorView actor: getInstructionData().getActorList()){
			if(clearStamps(actor)){
				toRet = 1;
			}
		};
		return toRet;
	}
	
	private boolean clearStamps(ActorView actor){
		if(actor instanceof TurtleView){
			return ((TurtleView)actor).getStamp().clear();
		}
		return false;
	}

}
