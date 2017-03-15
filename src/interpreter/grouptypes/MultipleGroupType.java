package interpreter.grouptypes;

import interpreter.misc.InstructionNode;
import interpreter.misc.InstructionTracker;

/**
 * BuilderUtil of type GroupStart, specific to instructions that require the
 * creation of multiple consecutive instructions
 * 
 * @author maddiebriere
 *
 */

public class MultipleGroupType extends GroupType {

	public MultipleGroupType(InstructionTracker track, String instruction, int numArgs) {
		super(track, instruction, numArgs);
	}

	/**
	 * WAY 2: The second way for Groups to be parsed is by treating the
	 * arguments as multiple commands.
	 * 
	 * One instruction that follows this pattern is "fd" This method would
	 * transform: ( fd 10 20 30 40 ) Into: fd 10 fd 20 fd 30 fd 40 Which our
	 * current parser can read
	 * 
	 * @return A String representing the new value for the child node
	 */
	@Override
	public String rearrangeWords() {
		int numArgs = getNumArgs();
		String instruction = getInstruction();

		String value = "";
		while (true) {
			InstructionNode currNode;
			String name = "";

			for (int i = 0; i < numArgs; i++) {
				if (!getTrack().isEmpty()) {
					currNode = getTrack().removeNext();
					getTrack().decrementCurrentText();
					name = currNode.getMyClassification();
					if (name.equals(getEndBracket())) {
						break;
					}
					if (i == 0) {
						value += instruction + " "; 
					}
					value += currNode.getMyCommand() + " ";
				}
			}
			if (name.equals(getEndBracket())) {
				break;
			}
		}
		value = removeSpace(value);
		return value;
	}

}
