package interpreter.util;

import java.util.List;

import user_structures.VariableData;

/**
 * Used to update variables and functions in the workspace without keeping old
 * versions
 * 
 * @author maddiebriere
 *
 */

public class WorkspaceUpdaterUtil {

	/**
	 * Generic addition of an item to a List (without repeats) -- used to
	 * accommodate ObservableLists in front-end
	 * 
	 * @param current
	 * @param toAdd
	 */
	public static <T> void add(List<T> current, T toAdd) {
		for (int i = 0; i < current.size(); i++) {
			if (current.get(i).equals(toAdd)) {
				current.remove(i);
				i--;
			}
		}
		current.add(toAdd);
	}

	/**
	 * Adds a variable with name 'name' and value 'value' to the given List of
	 * VariableData objects. If the variable already exists, reset its value.
	 * 
	 * @param current
	 *            The list of VariableData objects
	 * @param name
	 *            Name of the variable
	 * @param value
	 *            Value of the variable
	 */
	public static void varAdd(List<VariableData> current, String name, double value) {
		VariableData toAdd = new VariableData(name, value);
		for (int i = 0; i < current.size(); i++) {
			if (current.get(i).equals(toAdd)) {
				current.get(i).setValue(value); 
				return;
			}
		}
		current.add(toAdd);
	}
}
