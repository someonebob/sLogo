package instruction.DisplayCommands;


import java.util.Comparator;
import java.util.List;
import java.util.Map;

import exceptions.InvalidIndexException;

import instruction.InstructionData;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import property.ImageProperty;
import property.PenColorProperty;
import util.Pair;

public class GetShape extends DisplayCommand{
	private static final String RESOURCE_NONINDEXED_NAME = "NonindexedImageMessage";
	
	public GetShape(InstructionData instructionData, List<String> args, String myText) {
		super(instructionData, args, myText);
	}

	@Override
	public double execute() {
		return -1000;
		//In progress
		/*
		ImageProperty imageProperty = getInstructionData().getActiveActor().getImageProperty();
		List<Pair<String, ImageView>> indexedImages = imageProperty.getIndexedImages();
		ImageView currentImageView = imageProperty.getValue();
		Comparator<Pair<String, ImageView>> FilenameComparator = new Comparator<Pair<String, ImageView>>{
			public int compareTo(Pair<String, ImageView> pair1, Pair<String, ImageView> pair2){
				pair1.getMyB().compareTo(pair2.getMyB());
			}
		};
		for(Pair<String, ImageView> pair : indexedImages){
			
		}
		*/
		/*
		//Testing code
		ImageProperty imageProperty = getInstructionData().getActiveActor().getImageProperty();
		List<Pair<String, ImageView>> indexedImages = imageProperty.getIndexedImages();
		ImageView currentImageView = imageProperty.getValue();
		System.out.println(currentImageView.getImage());
		System.out.println("-----");
		for(Pair<String, ImageView> pair : indexedImages){
			System.out.println(pair.getMyA() + " " + pair.getMyB().getImage());
		}
		System.out.println("-----");
		return -1000;
		*/
		/*
		int index = indexedImages.indexOf(currentImage);  //TODO The .equals() problem, just make the default image the same object as in the List, and that's it
		if(index == -1){
			throw new InvalidIndexException(RESOURCE_NONINDEXED_NAME);
		}
		return index;
		*/

	}
	
	/*
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
	*/
	
}