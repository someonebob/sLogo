package interpreter.util;

import java.util.List;

/**
 * Used to update variables and functions in the workspace without
 * keeping old versions
 * 
 * @author maddiebriere
 *
 */

public class WorkspaceUpdater {
	
	public static <T> void add(List<T> current, T toAdd){
		for(int i=0; i<current.size(); i++){
			if(current.get(i).equals(toAdd)){
				current.remove(i);
				i--;
			}
		}
		current.add(toAdd);
	}
}
