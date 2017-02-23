package tool;

import java.util.List;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

/**
 * Defines a clickable tab in the ToolBar Examples: Language, Line color,
 * Background color
 * 
 * @author jimmy
 *
 */
public abstract class Tool
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
