package tool;

import java.util.Observer;

/**
 * 
 * @author Jesse
 *
 */
public interface Tool {
	public void addObservers(Observer ob);
	public abstract void makeItems();
	public Object getItem();
}
