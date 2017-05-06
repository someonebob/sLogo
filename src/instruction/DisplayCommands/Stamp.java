package instruction.DisplayCommands;

import java.util.List;

import instruction.ActorSpecificInstruction;
import instruction.InstructionData;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import view.StampView;

public class Stamp extends DisplayCommand implements ActorSpecificInstruction {

	public Stamp(InstructionData instructionData, List<String> args, String myText) {
		super(instructionData, args, myText);
	}

	@Override
	protected double execute() {
		StampView stamp = getInstructionData().getActiveStampView();
		Image current = this.getActiveActor().getImageView().getImage();
		Point2D location = this.getActiveActor().getLocation();
		stamp.addStamp(current, location);
		return 1;
	}

}
