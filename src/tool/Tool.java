package tool;

import java.util.List;

import javafx.scene.control.Menu;
import javafx.stage.Stage;

/**
 * Defines a clickable tab in the ToolBar Examples: Language, Line color,
 * Background color
 * 
 * @author jimmy
 *
 */
public abstract class Tool
{
	private Menu menu;
	private Stage stage;

	public Tool(String menuName, Stage stage)
	{
		menu = new Menu(menuName);
		this.stage = stage;
		makeMenuItems();
		if (getButtons() != null) {

			for (AbstractButton ab : getButtons()) {

				for (AbstractButton ab2 : ab.getItemList()) {
					menu.getItems().add(ab2.getItem());
				}

			}
		}

	}

	public Menu getMenu()
	{
		return menu;
	}

	public Stage getStage()
	{
		return stage;
	}

	public abstract void makeMenuItems();

	public abstract List<AbstractButton> getButtons();
}
