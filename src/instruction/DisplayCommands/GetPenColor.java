package instruction.DisplayCommands;

import java.util.List;

import exceptions.InvalidIndexException;
import instruction.ActorSpecificInstruction;
/**
 * Returns turtle's current color index
 *
 */
import instruction.InstructionData;
import javafx.scene.paint.Color;
import property.PenColorProperty;

/**
 * <p>
 * <b>SLogo Documentation:</b> Concrete Instruction subclass which returns the
 * index of the active actor's pen color, as archived in the
 * AbstractColorProperty class. If the pen color is not in this list, an
 * InvalidIndexException is thrown. This class implements
 * ActorSpecificInstruction and, as such, executes for every told actor.
 * </p>
 * 
 * <p>
 * <b>Java Backend Documentation:</b> It is assumed that no arguments to the
 * constructor are null, and that args contains the correct number of non-null
 * entries for this Instruction subclass. Dependencies are InstructionData,
 * List, String, PenView, PenColorProperty, Color, and InvalidIndexException.
 * </p>
 * 
 * @author Matthew Barbano
 *
 */
public class GetPenColor extends DisplayCommand implements ActorSpecificInstruction {
	private static final String RESOURCE_NONINDEXED_NAME = "NonindexedColorMessage";

	/**
	 * Standard 3-argument constructor for the Instruction hierarchy. Through a
	 * series of super() constructor calls up the hierarchy, sets 3
	 * corresponding variables in Instruction. No assumptions cause direct
	 * impact in this constructor. Setting any arguments or entries in args to
	 * null will cause errors elsewhere. Design decision: Making args a List
	 * accommodates SLogo commands with different numbers of arguments.
	 * 
	 * @param instructionData
	 *            for accessing frontend data
	 * @param args
	 *            for storing arguments to SLogo commands
	 * @param myText
	 *            a String representation of this Instruction
	 */
	public GetPenColor(InstructionData instructionData, List<String> args, String myText) {
		super(instructionData, args, myText);
	}

	/**
	 * Executes command as described in class Javadoc comment. Assumptions:
	 * Correct number of SLogo arguments, numerically valued SLogo arguments.
	 * Dependencies: InstructionData, PenView, PenColorProperty, List, Color.
	 * 
	 * @throws InvalidIndexException
	 *             if active actor's pen color not found in
	 *             AbstractColorProperty's list
	 * @return index of active actor's pen color
	 */
	@Override
	public double execute() {
		PenColorProperty penColorProperty = getInstructionData().getActivePenView().getPenColorProperty();
		List<Color> indexedColors = penColorProperty.getIndexedColors();
		Color currentColor = penColorProperty.getValue();
		int index = indexedColors.indexOf(currentColor);
		if (index == -1) {
			throw new InvalidIndexException(RESOURCE_NONINDEXED_NAME);
		}
		return index;
	}
}