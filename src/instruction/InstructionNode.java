package instruction;

/**
 * This class represents the nodes used in the parsed string
 * tree. This tree will hold the nodes (each representing its own
 * individual word) in an order conveying the argument relationship
 * between subsequent nodes.
 * 
 * @author maddiebriere
 *
 */

public interface InstructionNode {

	/**
	 * Get string representation of the instruction
	 * @return String representation of instruction (e.g., "fd 50")
	 */
	public String getText();
}
