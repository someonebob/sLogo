package instruction.DisplayCommands;


import java.util.List;

import exceptions.InvalidIndexException;
/**
 * Returns turtle's current color index
 *
 */
import instruction.InstructionData;
import javafx.scene.paint.Color;
import property.PenColorProperty;

public class GetPenColor extends DisplayCommand{
	private static final String RESOURCE_NONINDEXED_NAME = "NonindexedColorMessage";
	
	public GetPenColor(InstructionData instructionData, List<String> args, String myText) {
		super(instructionData, args, myText);
	}

	@Override
	public double execute() {
		PenColorProperty penColorProperty = getInstructionData().getActivePenView().getPenColorProperty();
		List<Color> indexedColors = penColorProperty.getIndexedColors();
		Color currentColor = penColorProperty.getValue();
		int index = indexedColors.indexOf(currentColor);
		if(index == -1){
			throw new InvalidIndexException(RESOURCE_NONINDEXED_NAME);
		}
		return index;
	}
}