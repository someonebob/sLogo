//This entire file is part of my masterpiece.
//Matthew Barbano
//See GenericDisplayCommand for description.
package instruction.DisplayCommands;
import java.util.List;
import instruction.ActorSpecificInstruction;
import instruction.InstructionData;
import javafx.scene.paint.Color;
import property.AbstractColorProperty;
import view.ActorView;
/**
 * <p><b>SLogo Documentation:</b> Concrete Instruction subclass which returns the
 * index of the active actor's pen color, as archived in the
 * AbstractColorProperty class. If the pen color is not in this list, an
 * InvalidIndexException is thrown. This class implements
 * ActorSpecificInstruction and, as such, executes for every told actor.</p>
 * <p><b>Java Backend Documentation:</b> @see DisplayCommand</p>
 * 
 * @author Matthew Barbano
 *
 */
public class GetPenColor extends DisplayAccessor<Color, Color, AbstractColorProperty>
		implements ActorSpecificInstruction {
	/**
	 * @see DisplayCommand constructor
	 */
	public GetPenColor(InstructionData instructionData, List<String> args, String myText) {
		super(instructionData, args, myText);
	}
	@Override
	protected AbstractColorProperty getActiveProperty(ActorView activeActor) {
		return activeActor.getPen().getPenColorProperty();
	}
	@Override
	protected Color convertPropertyTypeToCollectionType(Color targetInput) {
		return targetInput;
	}
}