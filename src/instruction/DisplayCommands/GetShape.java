package instruction.DisplayCommands;

import java.awt.Image;
import java.util.List;

import instruction.InstructionData;

public class GetShape extends DisplayCommand{

	public GetShape(InstructionData instructionData, List<String> args, String myText) {
		super(instructionData, args, myText);
	}

	@Override
	public double execute() {   //TODO / messy
		/*
		int index = 0;
		for(Image image : Defaults.ImageList)
		{
			index++;
			if(image.equals(getInstructionData().getActiveActor().getImage().getImage())
					{
				return index;
					}
		}
		*/
		//return getInstructionData().getActiveActor().getImageByIndex();
	}

}
