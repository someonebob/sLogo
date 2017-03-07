package user_structures;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class ID {
	private IntegerProperty id;
	
	public ID(int id){
		setID(id);
	}
	
	public IntegerProperty idProperty(){
		if(id == null){
			id = new SimpleIntegerProperty();
		}
		return id;
	}
	
	public void setID(int id){
		idProperty().set(id);
	}
	
	public int getID(){
		return idProperty().get();
	}
}
