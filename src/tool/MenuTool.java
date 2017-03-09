package tool;

import java.util.List;
import java.util.Observer;

import javafx.scene.control.Menu;
import javafx.stage.Stage;

/**
 * Defines a clickable tab in the ToolBar Examples: Language, Line color,
 * Background color
 * 
 * @author jimmy
 *
 */
public abstract class MenuTool implements Tool
{
	private Menu menu;
	private Stage stage;

	public MenuTool(String menuName, Stage stage)
	{
		menu = new Menu(menuName);
		this.stage = stage;
		makeItems();
		if (getButtons() != null) {

			for (AbstractMenuItem ab : getButtons()) {

				for (AbstractMenuItem ab2 : ab.getItemList()) {
					menu.getItems().add(ab2.getItem());
				}

			}
		}

	}

	public Menu getItem()
	{
		return menu;
	}

	protected Stage getStage()
	{
		return stage;
	}
	protected void addButtons(List<AbstractMenuItem> buttons, AbstractMenuItem... items){
		for(AbstractMenuItem item : items){
			buttons.add(item);
		}
	}
	protected abstract List<AbstractMenuItem> getButtons();
	@Override
	public void addObservers(Observer ob){
		for(AbstractMenuItem ab : getButtons()){
			ab.addObserver(ob);
		}
	}
}
