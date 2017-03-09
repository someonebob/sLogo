package property;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;

public class SpeedProperty extends Property<Double>
{
	public static final double MIN_FPS = .1;
	public static final double MAX_FPS = 20;
	public static final double DEFAULT_FPS = 5;

	public SpeedProperty(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setValue(String stringValue) {
		this.setValue(Double.valueOf(stringValue));
	}

	@Override
	public Node makeDynamicUpdater() {
		VBox vbox = new VBox();
		Label label = new Label(String.format("Set %s", this.getName()));
		Slider slider = new Slider(MIN_FPS, MAX_FPS, DEFAULT_FPS);
		slider.setShowTickLabels(true);
		slider.setMajorTickUnit(100);
		slider.setOnMouseReleased(e -> {
			this.setValue(slider.getValue());
		});
		
		vbox.getChildren().add(label);
		vbox.getChildren().add(slider);
		vbox.setAlignment(Pos.CENTER);
		
		return vbox;
	}

}
