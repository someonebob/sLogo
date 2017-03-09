package tool;

import javafx.scene.Node;
import javafx.scene.layout.VBox;

/**
 * 
 * @author Jesse
 *
 */
public class ComboBar implements SelectionBar{
	private VBox root;
	private SelectionBar menuBar;
	private SelectionBar toolBar;
	
	public ComboBar(){
		root = new VBox();
		menuBar = new SelectionMenuBar();
		toolBar = new SelectionToolBar();
		root.getChildren().addAll(menuBar.display(), toolBar.display());
		
	}

	@Override
	public void addTool(Tool tool) {
		if(tool instanceof MenuTool){
			menuBar.addTool(tool);
		}
		else if(tool instanceof ToolButton){
			toolBar.addTool(tool);
		}
	}

	@Override
	public void addAllTools(Tool... tools) {
		for(Tool t : tools){
			addTool(t);
		}
		
	}

	@Override
	public Node display() {
		return root;
	}

}
