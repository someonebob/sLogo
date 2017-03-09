package tool;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import javafx.scene.control.Control;

/**
 * 
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
	public void addItem(AbstractButton item)
	{
		itemList.add(item);
	}

	public Control getItem()
	{
		return myItem;
	}

	public List<AbstractButton> getItemList()
	{
		return itemList;
	}

}
