package instruction;

/**
 * This is located lower in the Instruction hierarchy. It represents the
 * Instruction of settings a variable to a value.
 * 
 * @author Matthew Barbano
 *
 */
public interface VariableDefinition {
	/**
	 * Updates the variable storage on VariablePage.
	 */
	public void updateStoredVariables();

	/**
	 * Returns the name of the variable set in this Instruction
	 * 
	 * @return
	 */
	public String getVariableIdentifier();

	/**
	 * Returns the value the variable is set to in this Instruction
	 * 
	 * @return
	 */
	public double getStoredValue();
}
