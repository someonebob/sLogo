package instruction;

/**
 * This is a the root of the inheritance hierarchy whose concrete subclasses
 * represent all possible commands in this project's implementation of the SLogo
 * language. Of greatest importance is its execute() method, which performs the
 * unique action associated with any given Instruction.
 * 
 * @author Matthew Barbano
 *
 */
public interface Instruction {
	/**
	 * Returns the instructionData object associated with this object
	 * 
	 * @return
	 */
	public InstructionData getInstructionData();

	/**
	 * Sets the instructionData object associated with this object to
	 * instructionData
	 * 
	 * @param instructionData
	 */
	public void setInstructionData(InstructionData instructionData);

	/**
	 * Performs the action associated with this Instruction. Unique for each
	 * concrete subclass implementation.
	 */
	public void execute();

	/**
	 * Returns the text originally input for an instruction, such as “Forward
	 * 50.” It is used by the LogoController to display previously entered
	 * commands. It can be used by future programmers if parsing a command’s
	 * text in a new Instruction becomes necessary.
	 **/
	public void getText();

}
