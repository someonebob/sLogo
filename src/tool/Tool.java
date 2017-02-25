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
public abstract class Tool{
	Menu menu;

	public Tool(String menuName) {
		menu = new Menu(menuName);
		makeMenuItems();
		if (getButtons() != null) {

			for (AbstractButton ab : getButtons()) {

				for (AbstractButton ab2 : ab.getItemList()) {
					menu.getItems().add(ab2.getItem());
				}

			}
		}

	}

	public Menu getMenu() {
		return menu;
	}

	public abstract void makeMenuItems();

	public abstract List<AbstractButton> getButtons();
}
