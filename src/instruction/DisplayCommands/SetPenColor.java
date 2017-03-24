package instruction.DisplayCommands;

/**
 * <p>
 * <b>SLogo Documentation:</b> Concrete Instruction subclass which sets active
 * actor's pen color to color corresponding to index specified in the single
 * SLogo argument. Color indices correspond to entries in AbstractColorProperty's List.
 * An InvalidIndexException is thrown if an invalid index is entered. This class implements ActorSpecificInstruction and, as such, executes
 * for every told actor.
 * </p>
 * <p><b>Java Backend Documentation:</b> @see DisplayCommand</p>
 * 
 * @author Matthew Barbano
 *
 */
import java.util.List;

import instruction.ActorSpecificInstruction;
import instruction.InstructionData;
import javafx.scene.paint.Color;
import property.AbstractColorProperty;
import view.ActorView;

public class SetPenColor extends DisplayModifier<Color, Color, AbstractColorProperty>
		implements ActorSpecificInstruction {

	/**
	 * @see DisplayCommand constructor
	 */
	public SetPenColor(InstructionData instructionData, List<String> args, String myText) {
		super(instructionData, args, myText);
	}

	@Override
	protected void updateProperty(Color newValue) {
		getInstructionData().getActivePenView().setColor(newValue);
	}

	@Override
	protected AbstractColorProperty getActiveProperty(ActorView activeActorView) {
		return getActiveActor().getPen().getPenColorProperty();
	}

	@Override
	protected Color convertCollectionTypeToPropertyType(Color targetInput) {
		return targetInput;
	}
}