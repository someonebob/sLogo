package instruction.DisplayCommands;

import java.util.List;

import instruction.ActorSpecificInstruction;
import instruction.InstructionData;
import util.ImageViewTuple;
import util.MathUtil;

/**
 * Sets shape of turtle to that represented by index
	returns given index
 * 
 * @author maddiebriere
 *
 */

public class SetShape extends DisplayCommand  implements ActorSpecificInstruction {

	public SetShape(InstructionData instructionData, List<String> args, String myText) {
		super(instructionData, args, myText);
	}

	@Override
	public double execute() {
		List<ImageViewTuple> indexedImages = getInstructionData().getActiveActor().getImageProperty().getIndexedImages();
		MathUtil.checkValidIndex(getArgumentDouble(0), indexedImages.size());
		ImageViewTuple newTuple = indexedImages.get((int)getArgumentDouble(0));
		getInstructionData().getActiveActor().setImage(newTuple.getImageView().getImage());
		return getArgumentDouble(0);
	}

}