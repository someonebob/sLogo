package user_structures;

import java.util.Stack;

import javafx.beans.property.*;

/**
 * Variable that holds String and double value
 * @author Jesse
 * @author maddiebriere
 *
 */
public class VariableData extends StructureData {
	private DoubleProperty value;
	private Stack<Double> values;
	
	public VariableData(String name, double value){
		super(name);
		values = new Stack<Double>();
		setValue(value);
	}
	
	public DoubleProperty valueProperty(){
		if(value == null){
			value = new SimpleDoubleProperty(0);
		}
		return value;
	}
	public void setValue(double value){
		if(!values.isEmpty())
			values.set(values.size()-1, value); //reset top value
		valueProperty().set(value);
	}
	
	public void addToStack(double value){
		values.add(value);
	}
	
	public double getValue(){
		return valueProperty().get();
	}
	
	public double popFromStack(){
		return values.pop();
	}
	
	public int getStackSize(){
		return values.size();
	}


}
