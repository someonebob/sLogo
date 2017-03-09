package instruction.DisplayCommands;

import java.util.List;
import java.util.Map;

import instruction.InstructionData;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import util.MathUtil;
import util.Pair;

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
		List<Pair<String, ImageView>> indexedImages = getInstructionData().getActiveActor().getImageProperty().getIndexedImages();
		MathUtil.checkValidIndex(getArgumentDouble(0), indexedImages.size());
		Pair<String, ImageView> targetPair = indexedImages.get((int)getArgumentDouble(0));
		getInstructionData().getActiveActor().setImage(targetPair.getMyB().getImage());
		return getArgumentDouble(0);
	}

}