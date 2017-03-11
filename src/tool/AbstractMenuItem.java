package tool;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import javafx.scene.control.MenuItem;

/**
 * Abstract class extended by every MenuItem in the Menu bar
 * Makes every item observable and handles commonalities
 * @author jimmy
 * @author Jesse
 *
 */
public abstract class AbstractMenuItem extends Observable
{
	private MenuItem myItem;
	private List<AbstractMenuItem> itemList;

	public AbstractMenuItem(MenuItem menu)
	{
		myItem = menu;
		itemList = new ArrayList<>();
		itemList.add(this);
	}

	public void addItem(AbstractMenuItem item)
	{
		itemList.add(item);
	}

	protected MenuItem getItem()
	{
		return myItem;
	}

	public List<AbstractMenuItem> getItemList()
	{
		return itemList;
	}
}
