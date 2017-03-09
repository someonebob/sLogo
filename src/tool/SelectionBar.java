package tool;

import javafx.scene.Node;

/**
 * 
 * @author Jesse
 *
 */
public interface SelectionBar {
	public void addTool(Tool tool);
	public void addAllTools(Tool...tools);
	public Node display();

}
