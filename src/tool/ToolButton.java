package tool;

import java.util.List;
import java.util.Observer;

import javafx.scene.Node;
import javafx.scene.layout.HBox;

/**
 * 
 * @author Jesse
 *
 */
public abstract class ToolButton implements Tool{
	private HBox group;
	
	public ToolButton(){
		group = new HBox(10);
		makeItems();
		if(getButtons() != null){
			for(AbstractButton ab : getButtons()){
				group.getChildren().add(ab.getItem());
			}
		}
	}
	
	public abstract void makeItems();
	
	@Override
	public Node getItem(){
		return group;
	}
	
	protected abstract List<AbstractButton> getButtons();

	@Override
	public void addObservers(Observer ob) {
		for(AbstractButton ab : getButtons()){
			ab.addObserver(ob);
		}
		
	}
	
	
}
