package interpreter.util;

import java.util.List;

import user_structures.VariableData;

/**
 * Used to update variables and functions in the workspace without
 * keeping old versions
 * 
 * @author maddiebriere
 *
 */

public class WorkspaceUpdaterUtil {
	
	public static <T> void add(List<T> current, T toAdd){
		for(int i=0; i<current.size(); i++){
			if(current.get(i).equals(toAdd)){
				current.remove(i);
				i--;
			}
		}
		current.add(toAdd);
	}
	
	public static void varAdd(List<VariableData> current, String name, double value){
		VariableData toAdd = new VariableData(name,value);
		for(int i=0; i<current.size(); i++){
			if(current.get(i).equals(toAdd)){
				current.get(i).setValue(value); //change value of current top of stack
				return;
			}
		}
		current.add(toAdd);
	}
}
