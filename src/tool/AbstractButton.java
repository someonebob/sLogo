package tool;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import javafx.scene.control.Control;

/**
 * Abstract class extended by every button in the tool bar
 * Makes every button observable and handles commonalities
 * @author Jesse
 *
 */
public abstract class AbstractButton extends Observable{
	private Control myItem;
	private List<AbstractButton> itemList;
	
	public AbstractButton(Control item){
		myItem = item;
		itemList = new ArrayList<>();
		itemList.add(this);
		
	}
	/**
	 * Add an item to the group of buttons
	 * @param item
	 */
	public void addItem(AbstractButton item)
	{
		itemList.add(item);
	}
	
	/**
	 * 
	 * @returns the item itself, only used by the child class itself so that it can refer to itself
	 * 
	 */
	protected Control getItem()
	{
		return myItem;
	}
	/**
	 * 
	 * @returns the list of AbstractButtons created
	 */
	public List<AbstractButton> getItemList()
	{
		return itemList;
	}

}
