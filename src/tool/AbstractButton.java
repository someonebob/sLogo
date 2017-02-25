package tool;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import javafx.scene.control.MenuItem;

/**
 * @author jimmy
 * @author Jesse
 *
 */
public class AbstractButton extends Observable{
	private MenuItem myItem;
	private List<AbstractButton> itemList;
	
	public AbstractButton(MenuItem menu)
	{
		myItem = menu;
		itemList = new ArrayList<AbstractButton>();
		itemList.add(this);
	}
	
	public void addItem(AbstractButton item)
	{
		itemList.add(item);
	}
	
	public void setOnAction(Object arg)
	{
		myItem.setOnAction(e -> {
			this.setChanged();
			this.notifyObservers(arg);
		});
	}
	
	public MenuItem getItem(){
		return myItem;
	}
	
	public List<AbstractButton> getItemList(){
		return itemList;
	}
}
