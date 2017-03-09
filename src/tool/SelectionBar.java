package tool;

import javafx.scene.Node;

public interface SelectionBar {
	public void addTool(Tool tool);
	public void addAllTools(Tool...tools);
	public Node display();

}
