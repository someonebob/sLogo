package instruction.DisplayCommands;

import java.util.List;

import exceptions.InvalidIndexException;
import instruction.InstructionData;
/**
 * Set the screen background color to that represented 
 * by index
 * 
 * @author maddiebriere
 *
 */
import javafx.scene.paint.Color;
import util.MathUtil;

/**
 * <p>
 * <b>SLogo Documentation:</b> Concrete Instruction subclass which sets the
 * background color of the simulation tab currently displayed to the Color
 * corresponding to the index of its SLogo parameter. The background color index
 * refers to the List of indexed colors in AbstractColorProperty. The SLogo
 * parameter must be an integer between 0 and the length of this list - 1,
 * inclusive; if not, an InvalidIndexException is thrown.
 * </p>
 * 
 * <p>
 * <b>Java Backend Documentation:</b> It is assumed that no arguments to the
 * constructor are null, and that args contains the correct number of non-null
 * entries for this Instruction subclass. Dependencies are InstructionData,
 * List, String, BackgroundColorProperty, Color, MathUtil, and
 * InvalidIndexException.
 * </p>
 * 
 * @author Matthew Barbano
 *
 */
public class SetBackground extends DisplayCommand {

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
	public SetBackground(InstructionData instructionData, List<String> args, String myText) {
		super(instructionData, args, myText);
	}

	/**
	 * Executes command as described in class Javadoc comment. Assumptions:
	 * Correct number of SLogo arguments, numerically valued SLogo arguments,
	 * SLogo parameter is a valid index. Dependencies: InstructionData,
	 * BackgroundColorProperty, Color, List, MathUtil, Instruction.
	 * 
	 * @throws InvalidIndexException
	 *             if 0 < SLogo parameter < length of indexed color list, SLogo
	 *             parameter is not an integer
	 * @return index specified by SLogo parameter
	 */
	@Override
	public double execute() {
		List<Color> indexedColors = getInstructionData().getBackgroundColorProperty().getIndexedColors();
		MathUtil.checkValidIndex(getArgumentDouble(0), indexedColors.size());
		Color newColor = indexedColors.get((int) getArgumentDouble(0));
		getInstructionData().getBackgroundColorProperty().setValue(newColor);
		return getArgumentDouble(0);
	}

}