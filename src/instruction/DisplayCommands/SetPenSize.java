package instruction.DisplayCommands;

import java.util.List;

import exceptions.InvalidIndexException;
import exceptions.NonsensicalArgumentException;
import instruction.ActorSpecificInstruction;
import instruction.InstructionData;
import property.PenThicknessProperty;

/**
 * <p>
 * <b>SLogo Documentation:</b> Concrete Instruction subclass which sets active
 * actor's pen thickness, in pixels, to value specified by its single SLogo
 * parameter. A NonsensicalArgumentException is thrown if a nonpositive number
 * of pixels is entered. This class implements ActorSpecificInstruction and, as
 * such, executes for every told actor.
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

public class SetPenSize extends DisplayCommand implements ActorSpecificInstruction {
	private static final String RESOURCE_NEGATIVE_NAME = "NonpositivePenThicknessMessage";

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
	public SetPenSize(InstructionData instructionData, List<String> args, String myText) {
		super(instructionData, args, myText);
	}

	/**
	 * Executes command as described in class Javadoc comment. Assumptions:
	 * Correct number of SLogo arguments, numerically valued SLogo arguments,
	 * SLogo parameter is a positive number of pixels. Dependencies:
	 * InstructionData, PenView, PenThicknessProperty, Instruction.
	 * 
	 * @throws InvalidIndexException
	 *             if SLogo parameter <= 0
	 * @return pixels specified by SLogo parameter
	 */
	@Override
	public double execute() {
		PenThicknessProperty penThicknessProperty = getInstructionData().getActivePenView().getPenThicknessProperty();
		if (getArgumentDouble(0) <= 0) {
			throw new NonsensicalArgumentException(RESOURCE_NEGATIVE_NAME);
		}
		penThicknessProperty.setValue(getArgumentDouble(0));
		return getArgumentDouble(0);
	}
}
