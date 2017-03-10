package tool;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;

public class ActorButtons extends ToolButton {
	private List<AbstractButton> buttons;
	
	public ActorButtons(){
		super();
	}


	@Override
	public void makeItems() {
		buttons = new ArrayList<>();
		addButtons(buttons, new CreateActorButton());
	}

	@Override
	protected List<AbstractButton> getButtons() {
		return buttons;
	}
	
	public class CreateActorButton extends AbstractButton{

		public CreateActorButton() {
			super(new Button("New Actor"));
			this.getItem().setOnMouseClicked(e -> {
				this.setChanged();
				this.notifyObservers();
			});
			
		}
		
	}
	
	public class ActorListButton extends AbstractButton{

		public ActorListButton() {
			super(new ComboBox());
			// TODO Auto-generated constructor stub
		}
		
	}

}
