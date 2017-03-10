package user_structures;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * superclass to VariableData and FunctionData to
 * remove duplicated code
 * 
 * @author maddiebriere
 *
 */

public abstract class StructureData implements Comparable<StructureData>{
	private StringProperty name;
	
	public StructureData(String name){
		setName(name);
	}
	
	@Override
	public int compareTo(StructureData o) {
		if(this == o){
			return 0;
		}
		return this.getName().compareTo(o.getName());
	}
	
	@Override
	public boolean equals(Object o){
		if (!(o instanceof StructureData)){
			return false;
			//TODO: Error handling
		}
		if(this == o){
			return true;
		}
		return this.getName().equals(((StructureData)o).getName());
	}
	
	public StringProperty nameProperty()
	{
		if (name == null) {
			//Might have messed this up
			name = new SimpleStringProperty(this, "");
		}
		return name;
	}

	public void setName(String value)
	{
		nameProperty().set(value);
	}

	public String getName()
	{
		return nameProperty().get();
	}

}
