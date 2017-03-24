package property;

/**
 * 
 * @author jimmy
 *
 */
public class PenThicknessProperty extends Property<Double>
{

	public PenThicknessProperty(String name)
	{
		super(name, Double.class);
	}

	@Override
	public void setValue(String stringValue)
	{
		this.setValue(Double.valueOf(stringValue));
	}

}
