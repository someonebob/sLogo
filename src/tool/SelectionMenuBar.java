package tool;

import javafx.scene.Node;
import javafx.scene.control.MenuBar;

/**
 * A bar which contains Tools, which say how properties of the display are
 * updated. Examples: Language, Line color, Background color
 * 
 * @author jimmy
 *
 */
public class SelectionMenuBar implements SelectionBar
{
	MenuBar mainMenu;

	public SelectionMenuBar()
	{
		mainMenu = new MenuBar();
	}

	/**
	 * Adds a new tool to the toolbar
	 * 
	 * @param tool
	 */
	@Override
	public void addTool(Tool tool)
	{
		mainMenu.getMenus().add(((MenuTool) tool).getItem());
	}
	
	/**
	 * Adds multiple tools to the toolbar
	 * @param tools
	 */
	@Override
	public void addAllTools(Tool...tools)
	{
		for(Tool t : tools){
			addTool(t);
		}
	}

	/**
	 * Returns the ToolBox display as a JavaFX.Node
	 * 
	 * @return JavaFX.Node
	 */
	public Node display()
	{
		return mainMenu;
	}
}