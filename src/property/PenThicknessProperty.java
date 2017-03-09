package property;

import java.util.Arrays;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

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
	public List<Node> makeDynamicUpdaters()
	{
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

		return Arrays.asList(label, slider);
	}
}
