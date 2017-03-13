package user_structures;

import java.util.Stack;

import javafx.beans.property.*;

/**
 * Variable that holds String and double value
 * 
 * NOTE: Variables are handled in a unique way here in order to accommodate
 * recursive function calls. Each VariableData holds a Stack of doubles
 * representing the values set for this variable. Hence, when a recursive call
 * is made, a VariableData maintains knowledge of all of its previous values
 * until it returns to the function call at which it was initialized. This
 * accommodates local vs. global variable scope.
 * 
 * @author maddiebriere
 * @author Jesse
 *
 */
public class VariableData extends StructureData {
	private DoubleProperty value;
	private Stack<Double> values;

	public VariableData(String name, double value) {
		super(name);
		values = new Stack<Double>();
		addToStack(value);
		setValue(value);
	}

	public DoubleProperty valueProperty() {
		if (value == null) {
			value = new SimpleDoubleProperty(0);
		}
		return value;
	}

	/**
	 * Set the top stack item to value and set the current value of the variable
	 * to value
	 * 
	 * @param value
	 *            Value with which to modify
	 */
	public void setValue(double value) {
		if (!values.isEmpty())
			values.set(values.size() - 1, value); // reset top value
		valueProperty().set(value);
	}

	/**
	 * Add the value to the stack of values for this variable
	 * 
	 * @param value
	 *            Value to add
	 */
	public void addToStack(double value) {
		values.add(value);
	}

	public double getValue() {
		return valueProperty().get();
	}

	/**
	 * Pop the variable from the stack and reset the current value of the
	 * variable to the newest top variable
	 * 
	 * @return the value of the popped 'setting'
	 */
	public double popFromStack() {
		double toRet = values.pop();
		if (values.size() != 0) {
			setValue(values.peek());
		}
		return toRet;
	}

	/**
	 * Get stack size
	 * 
	 * @return int representing stack size
	 */
	public int getStackSize() {
		return values.size();
	}

}
