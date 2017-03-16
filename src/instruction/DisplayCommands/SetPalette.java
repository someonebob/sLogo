package instruction.DisplayCommands;

import java.util.List;

import exceptions.InvalidIndexException;
import exceptions.NonsensicalArgumentException;
import instruction.InstructionData;
import javafx.scene.paint.Color;
import util.MathUtil;

/**
 * <p>
 * <b>SLogo Documentation:</b> Concrete Instruction subclass which adds a new
 * color to the indexed List of colors in AbstractColorProperty. The first SLogo
 * parameter specifies the index of the new Color. The second, third, and fourth
 * specify the RGB values of the new Color. The new Color can have indices in
 * range 0 to length of List, inclusive. If in range 0 to length of List - 1,
 * the old color is overwritten. If the new index = length of List, an
 * additional indexed color is added. If an invalid index or RGB value is
 * provided, an InvalidIndexException or NonsensicalArgumentException is thrown,
 * respectively.
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
public class SetPalette extends DisplayCommand {
	private static final String RESOURCE_RGB_NAME = "InvalidRGBMessage";

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
	public SetPalette(InstructionData instructionData, List<String> args, String myText) {
		super(instructionData, args, myText);
	}

	/**
	 * Executes command as described in class Javadoc comment. Assumptions:
	 * Correct number of SLogo arguments, numerically valued SLogo arguments,
	 * index argument is a valid integer, RGB arguments are integer values
	 * between 0-255, inclusive. Dependencies: InstructionData,
	 * BackgroundColorProperty, Color, List, MathUtil, Instruction.
	 * 
	 * @throws InvalidIndexException
	 *             if 0 < 1st SLogo parameter < length of indexed colors list +
	 *             1, 1st SLogo parameter is not an integer
	 * @throws NonsensicalArgumentException
	 *             2nd-4th SLogo parameters are not integers in range 0-255,
	 *             inclusive
	 * @return index specified by 1st SLogo parameter
	 */
	@Override
	public double execute() {
		List<Color> indexedColors = getInstructionData().getBackgroundColorProperty().getIndexedColors();
		MathUtil.checkValidIndex(getArgumentDouble(0), indexedColors.size() + 1);
		if (!MathUtil.hasIntegerValue(getArgumentDouble(1)) || !MathUtil.hasIntegerValue(getArgumentDouble(2))
				|| !MathUtil.hasIntegerValue(getArgumentDouble(3))) {
			throw new NonsensicalArgumentException(RESOURCE_RGB_NAME);
		}
		Color newColor;
		try {
			newColor = Color.rgb((int) getArgumentDouble(1), (int) getArgumentDouble(2), (int) getArgumentDouble(3));
		} catch (IllegalArgumentException exception) {
			throw new NonsensicalArgumentException(RESOURCE_RGB_NAME);
		}
		if ((int) getArgumentDouble(0) == indexedColors.size()) {
			indexedColors.add((int) getArgumentDouble(0), newColor);
		} else {
			indexedColors.set((int) getArgumentDouble(0), newColor);
		}
		getInstructionData().getBackgroundColorProperty().updateColorPalette();
		return getArgumentDouble(0);
	}

}