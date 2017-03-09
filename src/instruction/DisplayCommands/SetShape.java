package instruction.DisplayCommands;

import java.util.List;

import instruction.InstructionData;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import util.MathUtil;

/**
 * Sets shape of turtle to that represented by index
	returns given index
 * 
 * @author maddiebriere
 *
 */

public class SetShape extends DisplayCommand{

	public SetShape(InstructionData instructionData, List<String> args, String myText) {
		super(instructionData, args, myText);
	}

	@Override
	public double execute() {
		List<ImageView> indexedImages = getInstructionData().getActiveActor().getImageProperty().getIndexedImages();
		MathUtil.checkValidIndex(getArgumentDouble(0), indexedImages.size());
		ImageView imageView = indexedImages.get((int)getArgumentDouble(0));
		getInstructionData().getActiveActor().setImage(imageView.getImage());
		return getArgumentDouble(0);
	}

}