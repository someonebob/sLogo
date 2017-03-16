package instruction.DisplayCommands;

import java.util.List;

import exceptions.InvalidIndexException;
import instruction.ActorSpecificInstruction;
import instruction.InstructionData;
import util.ImageViewTuple;
import util.MathUtil;

/**
 * <p>
 * <b>SLogo Documentation:</b> Concrete Instruction subclass which sets active
 * actor's graphic to the graphic corresponding to index specified in the single
 * SLogo argument. Graphic indices correspond to entries in ImageProperty's
 * List. An InvalidIndexException is thrown if an invalid index is entered. This
 * class implements ActorSpecificInstruction and, as such, executes for every
 * told actor.
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

public class SetShape extends DisplayCommand implements ActorSpecificInstruction {

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
	public SetShape(InstructionData instructionData, List<String> args, String myText) {
		super(instructionData, args, myText);
	}

	/**
	 * Executes command as described in class Javadoc comment. Assumptions:
	 * Correct number of SLogo arguments, numerically valued SLogo arguments,
	 * SLogo argument is valid index. Dependencies: InstructionData,
	 * BackgroundColorProperty, Color, List, MathUtil, Instruction.
	 * 
	 * @throws InvalidIndexException
	 *             if 0 < SLogo parameter < length of indexed ImageViewTuple
	 *             List, SLogo parameter is not an integer
	 * @return index specified by SLogo parameter
	 */
	@Override
	public double execute() {
		List<ImageViewTuple> indexedImages = getInstructionData().getActiveActor().getImageProperty()
				.getIndexedImages();
		MathUtil.checkValidIndex(getArgumentDouble(0), indexedImages.size());
		ImageViewTuple newTuple = indexedImages.get((int) getArgumentDouble(0));
		getInstructionData().getActiveActor().setImage(newTuple.getImageView().getImage());
		return getArgumentDouble(0);
	}

}