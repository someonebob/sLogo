package tool;

import java.util.Observer;

/**
 * 
 * @author Jesse
 *
 */
public interface Tool {
	public void addObservers(Observer ob);
	public void makeItems();
	public Object getItem();
}
