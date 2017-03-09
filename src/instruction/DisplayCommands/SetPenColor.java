package instruction.DisplayCommands;

import java.util.List;

import instruction.InstructionData;
import javafx.scene.paint.Color;

public class SetPenColor extends DisplayCommand{

	public SetPenColor(InstructionData instructionData, List<String> args, String myText) {
		super(instructionData, args, myText);
	}

	@Override
	public double execute() {
		List<Color> penColorList = getInstructionData().getPenColorList();
		checkValidIndex(getArgumentDouble(0), penColorList.size());
		Color newColor = penColorList.get((int)getArgumentDouble(0));
		getInstructionData().getActiveActor().setPenColor(
		
		
		//NEED TO FINISH THIS
		
		checkValidIndex(getArgumentDouble(0), getInstructionData().getActiveActor().getPenColo)
		getInstructionData().getActiveActor().setPenColor((int)getArgumentDouble(0));  //TODO Update Jimmy
		return getArgumentDouble(0);
	}

}
