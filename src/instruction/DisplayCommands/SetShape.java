package instruction.DisplayCommands;

import java.util.List;

import instruction.ActorSpecificInstruction;
import instruction.InstructionData;
import javafx.scene.image.ImageView;
import property.ImageProperty;
import util.ImageViewTuple;
import view.ActorView;

/**
 * <p>
 * <b>SLogo Documentation:</b> Concrete Instruction subclass which sets active
 * actor's graphic to the graphic corresponding to index specified in the single
 * SLogo argument. Graphic indices correspond to entries in ImageProperty's
 * List. An InvalidIndexException is thrown if an invalid index is entered. This
 * class implements ActorSpecificInstruction and, as such, executes for every
 * told actor.
 * </p>
 * <p><b>Java Backend Documentation:</b> @see DisplayCommand</p>
 * 
 * @author Matthew Barbano
 *
 */

public class SetShape extends DisplayModifier<ImageViewTuple, ImageView, ImageProperty>
		implements ActorSpecificInstruction {

	/**
	 * @see DisplayCommand constructor
	 */
	public SetShape(InstructionData instructionData, List<String> args, String myText) {
		super(instructionData, args, myText);
	}

	@Override
	protected void updateProperty(ImageView newValue) {
		getInstructionData().getActiveActor().setImage(newValue.getImage());
	}

	@Override
	protected ImageProperty getActiveProperty(ActorView activeActorView) {
		return activeActorView.getImageProperty();
	}

	@Override
	protected ImageView convertCollectionTypeToPropertyType(ImageViewTuple targetInput) {
		return targetInput.getImageView();
	}
}