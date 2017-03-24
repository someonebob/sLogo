package property;

import java.util.List;

import javafx.scene.Node;

public class SpeedProperty extends Property<Double>
{
	public static final double MIN_FPS = .1;

	public static final double MAX_FPS = 50;
	public static final double DEFAULT_FPS = 5;

	public SpeedProperty(String name)
	{
		super(name, Double.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setValue(String stringValue)
	{
		this.setValue(Double.valueOf(stringValue));
	}

	@Override
	public List<Node> makeDynamicUpdaters()
	{
		this.setValue(DEFAULT_FPS);
		return new DynamicUpdaterFactory<Double>().makeDynamicUpdater(this, MIN_FPS, MAX_FPS);
	}

}
