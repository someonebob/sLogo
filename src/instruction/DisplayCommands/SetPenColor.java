package instruction.DisplayCommands;
/**
 * Sets color of the pen to that represented by index
	returns given index
 * 
 * @author maddiebriere
 *
 */

import java.util.List;

import instruction.InstructionData;
import javafx.scene.paint.Color;
import util.MathUtil;

public class SetPenColor extends DisplayCommand{

	public SetPenColor(InstructionData instructionData, List<String> args, String myText) {
		super(instructionData, args, myText);
	}

	@Override
	public double execute() {
		List<Color> indexedColors = getInstructionData().getActivePenView().getPenColorProperty().getIndexedColors();
		MathUtil.checkValidIndex(getArgumentDouble(0), indexedColors.size());
		Color newColor = indexedColors.get((int)getArgumentDouble(0));
		getInstructionData().getActivePenView().setColor(newColor);
		return getArgumentDouble(0);
	}

}