package tool;

import java.util.List;
import java.util.Observable;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

/**
 * Defines a clickable tab in the ToolBar Examples: Language, Line color,
 * Background color
 * 
 * @author jimmy
 *
 */
public abstract class Tool extends Observable
{
	Menu menu;

	public Tool(String menuName)
	{
		menu = new Menu(menuName);
		menu.getItems().addAll(makeMenuItems());
	}

	public Menu getMenu()
	{
		return menu;
	}

	public abstract List<MenuItem> makeMenuItems();
}
