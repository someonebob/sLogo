package property;

import java.util.List;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * Because of the intertwinings between xml and graphically updatable settings,
 * these methods are combined into one class--Property
 * 
 * @author jimmy
 *
 * @param <T>
 */
public abstract class Property<T>
{
	private ObjectProperty<T> value;
	// private T value;
	private Label valueLabel;
	private String name;
	private StringProperty stringValue;
	private Class<T> classType;

	public Property(String name, Class<T> type)
	{
		value = new SimpleObjectProperty<>();
		this.name = name;
		valueLabel = new Label(name + " value: " + this.getValue());
		stringValue = new SimpleStringProperty(name);
		classType = type;
	}

	public ObjectProperty<T> getProperty()
	{
		return value;
	}

	public T getValue()
	{
		return value.get();
	}

	public String getName()
	{
		return name;
	}

	public StringProperty getStringValue()
	{
		return stringValue;
	}

	public void setValue(T value)
	{
		this.value.set(value);
		updateDisplay();
		this.stringValue.set(name + ": " + this.getValue().toString());
	}

	public String convertToXML()
	{
		return "<" + name + ">" + value + "</" + name + ">";
	}

	public Node display()
	{
		return valueLabel;
	}

	protected void updateDisplay()
	{
		valueLabel.setText(name + " value : " + this.getValue().toString());
	}

	public abstract void setValue(String stringValue);

	public List<Node> makeDynamicUpdaters()
	{
		return new DynamicUpdaterFactory<T>().makeDynamicUpdater(this);
	}

	public Node displayDynamicUpdater()
	{
		VBox vbox = new VBox();
		vbox.getChildren().addAll(makeDynamicUpdaters());
		vbox.setAlignment(Pos.CENTER);
		return vbox;
	}

	public Class<T> getClassType()
	{
		return classType;
	}

}