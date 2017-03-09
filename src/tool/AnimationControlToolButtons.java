package tool;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

public class AnimationControlToolButtons extends ToolButton{
	public static final double MIN_FPS = .1;
	public static final double MAX_FPS = 1000;
	public static final double DEFAULT_FPS = 5;
	
	private List<AbstractButton> buttons;

	public AnimationControlToolButtons(){
		super();
	}
	
	@Override
	public void makeItems() {
		buttons = new ArrayList<>();
		buttons.add(new SliderLabel());
		buttons.add(new AnimationSlider());
	}
	
	@Override
	protected List<AbstractButton> getButtons() {
		return buttons;
	}
	
	public class SliderLabel extends AbstractButton{

		public SliderLabel() {
			super(new Label("FPS"));
		}
		
	}
	
	public class AnimationSlider extends AbstractButton{

		public AnimationSlider() {
			super(new Slider(MIN_FPS, MAX_FPS, DEFAULT_FPS));
			this.getItem().setOnMouseReleased(e -> {
				this.setChanged();
				this.notifyObservers(((Slider) this.getItem()).getValue());
			});
		}
		
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
