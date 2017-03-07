package user_structures;

import javafx.beans.property.*;

/**
 * Variable that holds String and double value
 * @author Jesse
 * @author maddiebriere
 *
 */
public class VariableData implements Comparable<VariableData> {
	private StringProperty name;
	private DoubleProperty value;
	
	public VariableData(String name, double value){
		setName(name);
		setValue(value);
	}
	
	public StringProperty nameProperty(){
		if(name == null){
			name = new SimpleStringProperty(this, "variable");
		}
		return name;
	}
	public void setName(String value){
		nameProperty().set(value);
	}
	public String getName(){
		return nameProperty().get();
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

	@Override
	public int compareTo(VariableData o) {
		if(this == o){
			return 0;
		}
		return this.getName().compareTo(o.getName());
	}
	
	@Override
	public boolean equals(Object o){
		if (!(o instanceof VariableData)){
			return false;
			//TODO: Error handling
		}
		if(this == o){
			return true;
		}
		return this.getName().equals(((VariableData)o).getName());
	}

}
