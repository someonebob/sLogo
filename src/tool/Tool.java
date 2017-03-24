// This entire file is part of my masterpiece.
// Jesse Yue
// This is the interface at the top of the hierarchy of my masterpiece which holds the basic methods needed for the Tools
package tool;

import java.util.Observer;

/**
 * This interface is the parent of all tools that are put in the selection bars
 * It encompasses the menus at the top and the buttons at the top
 * @author Jesse
 *
 */
public interface Tool {
	/**
	 * This method handles adding observers to all the observable tools
	 * @param ob is the observer that wants to observe
	 */
	public void addObservers(Observer ob);
	/**
	 * This method creates the AbstractButtons or AbstractMenuItems within each tool
	 */
	public void makeItems();
	/**
	 * 
	 * @returns the Tool to be displayed in the selectionbar
	 */
	public Object getItem();
}
