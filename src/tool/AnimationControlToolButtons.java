package tool;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.Button;
import javafx.scene.control.Control;


public class AnimationControlToolButtons extends ToolButton{
	
	
	private List<AbstractButton> buttons;

	public AnimationControlToolButtons(){
		super();
	}
	
	@Override
	public void makeItems() {
		buttons = new ArrayList<>();
		buttons.add(new AnimationPlayButton());
	}
	
	@Override
	protected List<AbstractButton> getButtons() {
		return buttons;
	}
	
	
	public class AnimationPlayButton extends AbstractButton{

		public static final boolean PLAYING = true;

		public AnimationPlayButton() {
			super(new Button("Play"));
			this.getItem().setOnMouseClicked(e -> {
				this.setChanged();
				this.notifyObservers(PLAYING);
			});
		}
		
	}
	
	public class AnimationPauseButton extends AbstractButton{
		public static final boolean PLAYING = false;

		public AnimationPauseButton() {
			super(new Button("Pause"));
			this.getItem().setOnMouseClicked(e -> {
				this.setChanged();
				this.notifyObservers(PLAYING);
			});
		}

	}


}
