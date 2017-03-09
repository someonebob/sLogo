package instruction.DisplayCommands;


import java.util.List;

import exceptions.InvalidIndexException;

import instruction.InstructionData;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import property.ImageProperty;
import property.PenColorProperty;

public class GetShape extends DisplayCommand{
	private static final String RESOURCE_NONINDEXED_NAME = "NonindexedImageMessage";
	
	public GetShape(InstructionData instructionData, List<String> args, String myText) {
		super(instructionData, args, myText);
	}

	@Override
	public double execute() {
		ImageProperty imageProperty = getInstructionData().getActiveActor().getImageProperty();
		List<ImageView> indexedImages = imageProperty.getIndexedImages();
		Image currentInnerImage = imageProperty.getValue().getImage();
		int index = -1;
		int counter = 0;
		for(ImageView imageView : indexedImages){
			if(imageView.getImage().impl_getUrl().equals(currentInnerImage.impl_getUrl())){
				index = counter;
			}
			counter++;   //TODO Trying to resolve .equals() problem
		}
		System.out.println("here");
		//int index = indexedImages.indexOf(currentImage);  //TODO The .equals() problem, just make the default image the same object as in the List, and that's it
		if(index == -1){
			throw new InvalidIndexException(RESOURCE_NONINDEXED_NAME);
		}
		return index;
	}
}