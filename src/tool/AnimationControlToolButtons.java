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
		this.addButtons(buttons, new AnimationPlayButton(), new AnimationPauseButton(), new AnimationStopButton());
	}
	
	@Override
	protected List<AbstractButton> getButtons() {
		return buttons;
	}
	
	
	public class AnimationPlayButton extends AbstractButton{


		public AnimationPlayButton() {
			super(new Button("Play"));
			this.getItem().setOnMouseClicked(e -> {
				this.setChanged();
				this.notifyObservers();
			});
		}
		
	}
	
	public class AnimationPauseButton extends AbstractButton{

		public AnimationPauseButton() {
			super(new Button("Pause"));
			this.getItem().setOnMouseClicked(e -> {
				this.setChanged();
				this.notifyObservers();
			});
		}

	}
	
	public class AnimationStopButton extends AbstractButton{

		public AnimationStopButton() {
			super(new Button("Stop"));
			this.getItem().setOnMouseClicked(e -> {
				this.setChanged();
				this.notifyObservers();
			});
		}
		
	}


}
