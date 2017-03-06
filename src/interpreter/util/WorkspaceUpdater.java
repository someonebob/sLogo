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
		for(T t : current){
			if(t.equals(toAdd)){
				current.remove(t);
			}
		}
		current.add(toAdd);
	}
}
