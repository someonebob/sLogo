package property;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;

/**
 * 
 * @author jimmy
 *
 */
public class PenThicknessProperty extends Property<Double>
{

	public PenThicknessProperty(String name)
	{
		super(name);
	}

	@Override
	public void setValue(Double thickness)
	{
		super.setValue(thickness);
	}

	@Override
	public void setValue(String stringValue)
	{
		this.setValue(Double.valueOf(stringValue));
	}

	@Override
	public Node makeDynamicUpdater()
	{
		VBox vbox = new VBox();
		Label label = new Label(String.format("Set %s", this.getName()));
		// TODO: REPLACE THESE WITH RESOURCE FILE
		Slider slider = new Slider();
		slider.setMin(1.0);
		slider.setMax(10.0);
		slider.setValue(this.getValue());
		slider.setShowTickLabels(true);
		slider.setMajorTickUnit(1);
		slider.setMinorTickCount(5);
		slider.valueProperty().addListener(new ChangeListener<Number>()
		{
			@Override
			public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val)
			{
				setValue((double) new_val);
			}
		});

		vbox.getChildren().add(label);
		vbox.getChildren().add(slider);

		return vbox;
	}
}
