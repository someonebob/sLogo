package tool;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Button;


public class AnimationControlToolButtons extends ToolButton{
	
	
	private List<AbstractButton> buttons;

	public AnimationControlToolButtons(){
		super();
	}
	
	@Override
	public void makeItems() {
		buttons = new ArrayList<>();
		buttons.add(new AnimationPlayPause());
	}
	
	@Override
	protected List<AbstractButton> getButtons() {
		return buttons;
	}
	
	
	public class AnimationPlayPause extends AbstractButton{

		public AnimationPlayPause() {
			super(new Button("Pause"));
			this.getItem().setOnMouseClicked(e -> {
				this.setChanged();
				this.notifyObservers();
			});
		}
		
	}


}
