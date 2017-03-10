package instruction.DisplayCommands;


import java.util.List;

import exceptions.InvalidIndexException;
import instruction.ActorSpecificInstruction;
import instruction.InstructionData;
import javafx.scene.image.ImageView;
import property.ImageProperty;
import util.ImageViewTuple;

public class GetShape extends DisplayCommand  implements ActorSpecificInstruction {
	private static final String RESOURCE_NONINDEXED_NAME = "NonindexedImageMessage";
	
	public GetShape(InstructionData instructionData, List<String> args, String myText) {
		super(instructionData, args, myText);
	}

	@Override
	public double execute() {
		ImageProperty imageProperty = getInstructionData().getActiveActor().getImageProperty();
		List<ImageViewTuple> indexedImages = imageProperty.getIndexedImages();
		ImageView currentImage = imageProperty.getValue();
		int index = indexedImages.indexOf(new ImageViewTuple("filler", currentImage));
		if(index == -1){
			throw new InvalidIndexException(RESOURCE_NONINDEXED_NAME);
		}
		return index;
	}
	

	
}