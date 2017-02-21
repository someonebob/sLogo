package instruction;

import java.util.List;

/**
 * This is located lower in the Instruction hierarchy. It represents the
 * Instruction of declaring a user-defined command.
 * 
 * @author Matthew Barbano
 *
 */
public interface CommandDefinition {
	/**
	 * Updates the old user commands stored in environment's history
	 */
	public void updateStoredUserCommands();

	/**
	 * Returns the name of the user-defined function
	 * 
	 * @return
	 */
	public String getCommandIdentifier();

	/**
	 * Returns true if this function returns a value. If not, returns false.
	 * 
	 * @return
	 */
	public boolean returnsValue();

	/**
	 * Returns a list of the argument names this function accepts.
	 * 
	 * @return
	 */
	public List<String> getCommandArguments();
}
