package user_structures;

import javafx.beans.property.*;

/**
 * Variable that holds String and double value
 * @author Jesse
 * @author maddiebriere
 *
 */
public class VariableData extends StructureData {
	private DoubleProperty value;
	
	public VariableData(String name, double value){
		super(name);
		setValue(value);
	}
	
	public DoubleProperty valueProperty(){
		if(value == null){
			value = new SimpleDoubleProperty(0);
		}
		return value;
	}
	public void setValue(double value){
		valueProperty().set(value);
	}
	public double getValue(){
		return valueProperty().get();
	}


}
