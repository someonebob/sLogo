package instruction.DisplayCommands;

import java.util.List;

import exceptions.NonsensicalArgumentException;
import instruction.ActorSpecificInstruction;
import instruction.InstructionData;
import property.PenThicknessProperty;

/**
 * Sets size of the pen to be pixels thickness
	returns given pixels
 * @author maddiebriere
 *
 */

public class SetPenSize extends DisplayCommand  implements ActorSpecificInstruction {
	private static final String RESOURCE_NEGATIVE_NAME = "NonpositivePenThicknessMessage";
	
	public SetPenSize(InstructionData instructionData, List<String> args, String myText) {
		super(instructionData, args, myText);
	}

	@Override
	public double execute() {
		PenThicknessProperty penThicknessProperty = getInstructionData().getActivePenView().getPenThicknessProperty();
		if(getArgumentDouble(0) <= 0){
			throw new NonsensicalArgumentException(RESOURCE_NEGATIVE_NAME);
		}
		penThicknessProperty.setValue(getArgumentDouble(0));
		return getArgumentDouble(0);
	}
}
