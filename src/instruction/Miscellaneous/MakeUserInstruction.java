package instruction.Miscellaneous;

import java.util.List;

import instruction.InstructionData;
import interpreter.clean.InstructionSplitter;
import user_structures.FunctionData;

/**
 * <p>
 * <b>SLogo Documentation:</b> Instruction for a user-defined instruction. This
 * class stores a String name that represents the command (e.g., dance, flower)
 * -- this name is given by the user. Also has a String that can be parsed upon
 * execution of this command to perform the intended functionality.
 * 
 * NOTE: The actual command is not executed, nor even parsed when the command is
 * created. Any problems with parsing created by the body of the function will
 * occur when you try to run the command, not when it is defined.
 * </p>
 * 
 * <p>
 * <b>Java Backend Documentation:</b> It is assumed that no arguments to the
 * constructor are null, and that args contains the correct number of non-null
 * entries for this Instruction subclass. Dependencies are InstructionData,
 * List, String, Instruction, InstructionSplitter, FunctionData and the helpers
 * in superclass Miscellaneous.
 * </p>
 * 
 * @author maddiebriere
 *
 */

public class MakeUserInstruction extends Miscellaneous {

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
	public MakeUserInstruction(InstructionData instructionData, List<String> args, String myText) {
		super(instructionData, args, myText);
	}

	/**
	 * Executes command as described in class Javadoc comment. Assumptions:
	 * Correct number of SLogo arguments, numerically valued SLogo arguments.
	 * Dependencies: Instruction, FunctionData, List, and the helpers in
	 * superclass Miscellaneous.
	 * 
	 * @return 0
	 */
	@Override
	public double execute() {
		List<String> args = InstructionSplitter.getInstructionStrings(getArgumentString(1));
		for (int i = 0; i < args.size(); i++) {
			args.set(i, args.get(i).substring(1));
		}
		FunctionData function = new FunctionData(getArgumentString(0), getArgumentString(2), args);
		getInstructionData().addFunction(function);

		return 0;
	}

}
