package user_structures;

import java.util.ArrayList;
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
public class FunctionData extends StructureData 
{
	private StringProperty commands;
	private List<String> args; 
	
	public FunctionData(String name, String commands, List<String> args){

		super(name);
		setCommands(commands);
		setArgs(args);
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
	
	public List<String> argsProperty(){
		if(args == null){
			args = new ArrayList<String>();
		}
		return args;
	}
	public void setArgs(List<String> args){
		argsProperty().addAll(args);
	}
	
	public List<String> getArgs(){
		return argsProperty();
	}
	
}
