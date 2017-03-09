package instruction.DisplayCommands;

import java.util.List;

import instruction.InstructionData;
import javafx.scene.paint.Color;

public class SetPalette extends DisplayCommand{
	private static final double OPACITY = 1.0;
	
	public SetPalette(InstructionData instructionData, List<String> args, String myText) {
		super(instructionData, args, myText);
	}

	@Override
	public double execute() {
		checkValidIndex(getArgumentDouble(0), getInstructionData().getBackgroundColorList().size());
		Color color = new Color(getArgumentDouble(1), getArgumentDouble(2), getArgumentDouble(3), OPACITY);
		getInstructionData().getBackgroundColorList().add((int)getArgumentDouble(0), color);
		return getArgumentDouble(0);
	}

}