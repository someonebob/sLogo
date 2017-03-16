package instruction.DisplayCommands;

/**
 * <p>
 * <b>SLogo Documentation:</b> Concrete Instruction subclass which sets active
 * actor's pen color to color corresponding to index specified in the single
 * SLogo argument. Color indices correspond to entries in AbstractColorProperty's List.
 * An InvalidIndexException is thrown if an invalid index is entered. This class implements ActorSpecificInstruction and, as such, executes
 * for every told actor.
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
import java.util.List;

import exceptions.InvalidIndexException;
import instruction.ActorSpecificInstruction;
import instruction.InstructionData;
import javafx.scene.paint.Color;
import util.MathUtil;

public class SetPenColor extends DisplayCommand implements ActorSpecificInstruction {

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
	public SetPenColor(InstructionData instructionData, List<String> args, String myText) {
		super(instructionData, args, myText);
	}

	/**
	 * Executes command as described in class Javadoc comment. Assumptions:
	 * Correct number of SLogo arguments, numerically valued SLogo arguments,
	 * SLogo parameter is a valid index. Dependencies: InstructionData, PenView,
	 * PenColorProperty, Color, List, MathUtil, Instruction.
	 * 
	 * @throws InvalidIndexException
	 *             if 0 < SLogo parameter < length of indexed color list, SLogo
	 *             parameter is not an integer
	 * @return index specified by SLogo parameter
	 */
	@Override
	public double execute() {
		List<Color> indexedColors = getInstructionData().getActivePenView().getPenColorProperty().getIndexedColors();
		MathUtil.checkValidIndex(getArgumentDouble(0), indexedColors.size());
		Color newColor = indexedColors.get((int) getArgumentDouble(0));
		getInstructionData().getActivePenView().setColor(newColor);
		return getArgumentDouble(0);
	}

}