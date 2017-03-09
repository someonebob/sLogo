package instruction.DisplayCommands;

/**
 * Sets shape of turtle to that represented by index
	returns given index
 * 
 * @author maddiebriere
 *
 */

public class SetShape {

public class SetShape extends DisplayCommand{

	public SetShape(InstructionData instructionData, List<String> args, String myText) {
		super(instructionData, args, myText);
	}

	@Override
	public double execute() {
		//getInstructionData().getActiveActor().;
		return getArgumentDouble(0);
	}

}