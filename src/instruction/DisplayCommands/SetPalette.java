package instruction.DisplayCommands;

import java.util.List;

import instruction.InstructionData;
import javafx.scene.paint.Color;

public class SetPalette extends DisplayCommand{

	public SetPalette(InstructionData instructionData, List<String> args, String myText) {
		super(instructionData, args, myText);
	}

	@Override
	public double execute() {
		Color color = new Color(getArgumentDouble(1), getArgumentDouble(2), getArgumentDouble(3), 1.0);
		getInstructionData().setPalette((int)getArgumentDouble(0), color);
		return getArgumentDouble(0);
	}

}
