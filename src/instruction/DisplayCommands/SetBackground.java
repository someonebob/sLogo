package instruction.DisplayCommands;
import java.util.List;

import instruction.InstructionData;
/**
 * Set the screen background color to that represented 
 * by index
 * 
 * @author maddiebriere
 *
 */
import javafx.scene.paint.Color;
import util.MathUtil;

public class SetBackground extends DisplayCommand{

	public SetBackground(InstructionData instructionData, List<String> args, String myText) {
		super(instructionData, args, myText);
	}

	@Override
	public double execute() {
		List<Color> indexedColors = getInstructionData().getSimulation().getBackgroundColorProperty().getIndexedColors();
		MathUtil.checkValidIndex(getArgumentDouble(0), indexedColors.size());
		Color newColor = indexedColors.get((int)getArgumentDouble(0));
		getInstructionData().getSimulation().getBackgroundColorProperty().setValue(newColor);
		return getArgumentDouble(0);
	}
	
}