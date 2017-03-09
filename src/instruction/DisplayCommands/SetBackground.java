package instruction.DisplayCommands;
/**
 * Set the screen background color to that represented 
 * by index
 * 
 * @author maddiebriere
 *
 */

import java.util.List;

import instruction.InstructionData;
import javafx.scene.paint.Color;

public class SetBackground extends DisplayCommand{

	public SetBackground(InstructionData instructionData, List<String> args, String myText) {
		super(instructionData, args, myText);
	}

	@Override
	public double execute() {
		checkValidIndex(getArgumentDouble(0), getInstructionData().getBackgroundColorList().size());
		Color color = getInstructionData().getBackgroundColorList()
				
		
		Color color = getInstructionData().getBackgroundColorList().get((int)getArgumentDouble(0));
		getInstructionData().getSimulation().setBackgroundColor(color);
		return getArgumentDouble(0);
	}
	
}
