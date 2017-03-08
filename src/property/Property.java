package property;

import javafx.scene.Node;

/**
 * Because of the intertwinings between xml and graphically updatable settings,
 * these methods are intertwined into one class--Property
 * 
 * @author jimmy
 *
 * @param <T>
 */
public abstract class Property<T>
{
	private T value;
	private String name;

	public Property(String name)
	{
		this.name = name;
	}

	public T getValue()
	{
		return value;
	}

	public String getName()
	{
		return name;
	}

	public void setValue(T value)
	{
		this.value = value;
	}

	public String convertToXML()
	{
		return "<" + name + ">" + value + "</" + name + ">";
	}

	public abstract void setValue(String stringValue);

	public abstract Node makeDynamicUpdater();

}