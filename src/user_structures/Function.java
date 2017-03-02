package user_structures;

import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * A user-defined function that holds a name, and a String representing the
 * block of code stored in this function
 * 
 * @author maddiebriere
 *
 */
public class Function
{
	private StringProperty name;
	private StringProperty commands;
	private List<StringProperty> args; // TODO: Implement

	public Function(String name, String commands)
	{
		setName(name);
		setCommands(commands);
	}

	public StringProperty nameProperty()
	{
		if (name == null) {
			name = new SimpleStringProperty(this, "variable");
		}
		return name;
	}

	public void setName(String value)
	{
		nameProperty().set(value);
	}

	public String getName()
	{
		return nameProperty().get();
	}

	public StringProperty commandsProperty()
	{
		if (commands == null) {
			commands = new SimpleStringProperty("");
		}
		return commands;
	}

	public void setCommands(String commands)
	{
		commandsProperty().set(commands);
	}

	public String getCommands()
	{
		return commandsProperty().get();
	}

}
