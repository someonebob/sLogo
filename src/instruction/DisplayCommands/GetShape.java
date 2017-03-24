package instruction.DisplayCommands;

import java.util.List;

import instruction.ActorSpecificInstruction;
import instruction.InstructionData;
import javafx.scene.image.ImageView;
import property.ImageProperty;
import util.ImageViewTuple;
import view.ActorView;

/**
 * <p><b>SLogo Documentation:</b> Concrete Instruction subclass which returns the
 * index of the active actor's graphic, as archived in the ImageProperty class.
 * If the graphic is not in this list, an InvalidIndexException is thrown.
 * However, in the current implementation of this project, it should never be
 * thrown since ImageProperty indexes all graphics in the appropriate folder on
 * the file system. This class implements ActorSpecificInstruction and, as such,
 * executes for every told actor.</p>
 * <p><b>Java Backend Documentation:</b> @see DisplayCommand</p>
 * 
 * @author Matthew Barbano
 *
 */
public class GetShape extends DisplayAccessor<ImageViewTuple, ImageView, ImageProperty> implements ActorSpecificInstruction {

	/**
	 * @see DisplayCommand constructor
	 */
	public GetShape(InstructionData instructionData, List<String> args, String myText) {
		super(instructionData, args, myText);
	}

	@Override
	protected ImageProperty getActiveProperty(ActorView activeActor) {
		return activeActor.getImageProperty();
	}

	@Override
	protected ImageViewTuple convertPropertyTypeToCollectionType(ImageView targetInput) {
		return new ImageViewTuple("", targetInput);
	}
}