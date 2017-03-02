package instruction;

import java.util.Arrays;
import java.util.List;

import javafx.geometry.Bounds;
import user_structures.Function;
import user_structures.Variable;
import view.ActorView;
import view.SimulationView;

/**
 * This acts as container for all of the data associated with the current state
 * of the simulation. It is used by Interpreter's subclasses to access and
 * modify different elements of the simulation - for example, actors' positions
 * and headings.
 * 
 * @author Matthew Barbano
 * @author maddiebriere
 *
 */
public class InstructionData
{
	SimulationView simulation;
	List<Variable> variables;
	List<Function> functions;

	public InstructionData()
	{
		// TODO: Implement default constructor
	}

	public InstructionData(SimulationView simulation, List<Variable> variables, List<Function> functions)
	{ 
		this.simulation = simulation;
		this.variables = variables;
		this.functions = functions;
	}

	public ActorView getActiveActor()
	{
		return simulation.getTurtle();
	}

	/**
	 * Returns the List of Actors held by this class
	 * 
	 * @return
	 */
	public List<ActorView> getActors()
	{
		return Arrays.asList(simulation.getTurtle());
	}

	public Bounds getSimulationBounds()
	{
		return simulation.getBounds();
	}
	
	public List<Variable> getVariables(){
		return variables;
	}
	
	public List<Function> getFunctions(){
		return functions;
	}
	
	/**
	 * Checks if the current workspace contains a variable of the 
	 * name given and returns that variable if it does
	 * 
	 * @param variableName The potential variable name
	 * @return Variable matching to the current name, otherwise null
	 */
	public Variable containsVariable(String variableName){
		for(Variable v: variables){
			if(v.getName().equals(variableName)){
				return v;
			}
		}
		return null;
	}
	
	/**
	 * Checks if the current workspace contains a function of the 
	 * name given and returns that function if it does
	 * 
	 * @param functionName The potential function name
	 * @return Function matching to the current name, otherwise null
	 */
	public Function containsFunction(String functionName){
		for(Function f:functions){
			if(f.getName().equals(functionName)){
				return f;
			}
		}
		return null;
	}
	
	public double getVariableValue(String variableName){
		for(Variable v: variables){
			if(v.getName().equals(variableName)){
				return v.getValue();
			}
		}
		return 0;
	}
	
	public String getFunctionValue(String functionName){
		for(Function f:functions){
			if(f.getName().equals(functionName)){
				return f.getCommands();
			}
		}
		return null;
	}
	
	public void addVariable(Variable v){
		variables.add(v);
	}
	
	public void addFunction(Function f){
		functions.add(f);
	}
}
