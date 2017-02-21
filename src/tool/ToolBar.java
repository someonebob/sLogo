package tool;

import java.util.List;

import javafx.scene.Node;

/**
 * A bar which contains Tools, which say how properties of the display are
 * updated. Examples: Language, Line color, Background color
 * 
 * @author jimmy
 *
 */
public interface ToolBar
{
	/**
	 * Adds a new tool to the toolbar
	 * 
	 * @param tool
	 */
	public void addTool(Tool tool);

	/**
	 * Returns the list of tools that are in the toolbar
	 * 
	 * @return List of tools
	 */
	public List<Tool> getTools();

	/**
	 * Returns the ToolBox display as a JavaFX.Node
	 * 
	 * @return JavaFX.Node
	 */
	public Node display();
}