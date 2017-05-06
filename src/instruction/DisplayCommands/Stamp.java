package instruction.DisplayCommands;

import java.util.List;

import instruction.InstructionData;
import view.TurtleView;

public class Stamp extends DisplayCommand {
	
	
	public Stamp(InstructionData instructionData, List<String> args, String myText) {
		super(instructionData, args, myText);
	}

	@Override
	protected double execute() {
		TurtleView stamp = ((TurtleView) getActiveActor()).clone();
		getInstructionData().getStamps().add(stamp);   //polymorphism: subclass clone() should execute (check with print statement to ensure)
		getInstructionData().drawStamp(stamp);
		return getActiveImageIndex();
	}

}
