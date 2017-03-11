package tool;

import java.util.List;
import java.util.Observer;

import javafx.scene.Node;
import javafx.scene.control.Separator;
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
		group.getChildren().add(new Separator());
	}
		
	@Override
	public Node getItem(){
		return group;
	}
	
	/**
	 * This allows us to add multiple buttons to the list at a time
	 * @param buttons
	 * @param items
	 */
	protected void addButtons(List<AbstractButton> buttons, AbstractButton... items){
		for(AbstractButton item : items){
			buttons.add(item);
		}
	}
	/**
	 * 
	 * @returns the list of AbstractButtons to loop over them to add each to the tool 
	 */
	protected abstract List<AbstractButton> getButtons();

	@Override
	public void addObservers(Observer ob) {
		for(AbstractButton ab : getButtons()){
			ab.addObserver(ob);
		}
		
	}
	
	
}
