package tool;

import javafx.scene.Node;
import javafx.scene.control.ToolBar;

/**
 * 
 * @author Jesse
 *
 */
public class SelectionToolBar implements SelectionBar{
	private ToolBar bar;
	
	public SelectionToolBar(){
		bar = new ToolBar();
	}

	@Override
	public void addTool(Tool tool) {
		bar.getItems().add(((ToolButton) tool).getItem());
	}

	@Override
	public void addAllTools(Tool... tools) {
		for(Tool t : tools){
			addTool(t);
		}
	}

	@Override
	public Node display() {
		return bar;
	}

}
