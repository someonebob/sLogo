package instruction.DisplayCommands;

/**
 * Returns turtle's current color index
 * 
 * @author maddiebriere
 *
 */

public class GetPenColor {

import instruction.InstructionData;

public class GetPenColor extends DisplayCommand{

	public GetPenColor(InstructionData instructionData, List<String> args, String myText) {
		super(instructionData, args, myText);
	}

	@Override
	public double execute() {
		return getInstructionData().getActiveActor().getPenColorIndex();  //TODO Update Jimmy
	}
}