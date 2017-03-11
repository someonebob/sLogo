package instruction.DisplayCommands;

import java.util.List;

import exceptions.NonsensicalArgumentException;
import instruction.InstructionData;
import javafx.scene.paint.Color;
import util.MathUtil;

public class SetPalette extends DisplayCommand
{
	private static final String RESOURCE_RGB_NAME = "InvalidRGBMessage";

	public SetPalette(InstructionData instructionData, List<String> args, String myText)
	{
		super(instructionData, args, myText);
	}

	@Override
	public double execute()
	{
		List<Color> indexedColors = getInstructionData().getSimulation().getBackgroundColorProperty()
				.getIndexedColors();
		MathUtil.checkValidIndex(getArgumentDouble(0), indexedColors.size() + 1);
		if (!MathUtil.hasIntegerValue(getArgumentDouble(1)) || !MathUtil.hasIntegerValue(getArgumentDouble(2))
				|| !MathUtil.hasIntegerValue(getArgumentDouble(3))) {
			throw new NonsensicalArgumentException(RESOURCE_RGB_NAME);
		}
		Color newColor;
		try {
			newColor = Color.rgb((int) getArgumentDouble(1), (int) getArgumentDouble(2), (int) getArgumentDouble(3));
		} catch (IllegalArgumentException exception) {
			throw new NonsensicalArgumentException(RESOURCE_RGB_NAME);
		}
		if ((int) getArgumentDouble(0) == indexedColors.size()) {
			indexedColors.add((int) getArgumentDouble(0), newColor);
		} else {
			indexedColors.set((int) getArgumentDouble(0), newColor);
		}
		return getArgumentDouble(0);
	}

}