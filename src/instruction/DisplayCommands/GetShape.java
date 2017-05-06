package instruction.DisplayCommands;

import java.util.List;

import exceptions.InvalidIndexException;
import instruction.ActorSpecificInstruction;
import instruction.InstructionData;
import javafx.scene.image.ImageView;
import property.ImageProperty;
import util.ImageViewTuple;

/**
 * <p>
 * <b>SLogo Documentation:</b> Concrete Instruction subclass which returns the
 * index of the active actor's graphic, as archived in the ImageProperty class.
 * If the graphic is not in this list, an InvalidIndexException is thrown.
 * However, in the current implementation of this project, it should never be
 * thrown since ImageProperty indexes all graphics in the appropriate folder on
 * the file system. This class implements ActorSpecificInstruction and, as such,
 * executes for every told actor.
 * </p>
 * 
 * <p>
 * <b>Java Backend Documentation:</b> It is assumed that no arguments to the
 * constructor are null, and that args contains the correct number of non-null
 * entries for this Instruction subclass. Dependencies are InstructionData,
 * List, String, ActorView, ImageProperty, ImageViewTuple, ImageView, and
 * InvalidIndexException.
 * </p>
 * 
 * @author Matthew Barbano
 *
 */
public class GetShape extends DisplayCommand implements ActorSpecificInstruction {
	

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
	public GetShape(InstructionData instructionData, List<String> args, String myText) {
		super(instructionData, args, myText);
	}

	/**
	 * Executes command as described in class Javadoc comment. Assumptions:
	 * Correct number of SLogo arguments, numerically valued SLogo arguments,
	 * actor's graphic is contained within ImageProperty's list. Dependencies:
	 * InstructionData, ActorView, ImageProperty, List, ImagePropertyTuple,
	 * ImageView, InvalidIndexException.
	 * 
	 * @throws InvalidIndexException
	 *             if displayed graphic not found in ImageProperty's list
	 * @return index of active actor's pen color
	 */
	@Override
	public double execute() {
		return getActiveImageIndex();
	}

}