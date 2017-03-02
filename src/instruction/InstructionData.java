package instruction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.geometry.Bounds;
import models.Simulation;
import user_structures.FunctionData;
import user_structures.VariableData;
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
	List<VariableData> variables;
	List<FunctionData> functions;

	public InstructionData()
	{
		this.simulation = new SimulationView();
		this.variables = new ArrayList<VariableData>();
		this.functions = new ArrayList<FunctionData>();
	}

	public InstructionData(SimulationView simulation, List<VariableData> variables, List<FunctionData> functions)
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
	
	public List<VariableData> getVariables(){
		return variables;
	}
	
	public List<FunctionData> getFunctions(){
		return functions;
	}
	
	/**
	 * Checks if the current workspace contains a variable of the 
	 * name given and returns that variable if it does
	 * 
	 * @param variableName The potential variable name
	 * @return Variable matching to the current name, otherwise null
	 */
	public VariableData containsVariable(String variableName){
		for(VariableData v: variables){
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
	public FunctionData containsFunction(String functionName){
		for(FunctionData f:functions){
			if(f.getName().equals(functionName)){
				return f;
			}
		}
		return null;
	}
	
	public String getVariableValue(String variableName){
		for(VariableData v: variables){
			if(v.getName().equals(variableName)){
				return ""+v.getValue();
			}
		}
		return "NO MATCH";
	}
	
	public String getFunctionValue(String functionName){
		for(FunctionData f:functions){
			if(f.getName().equals(functionName)){
				return f.getCommands();
			}
		}
		return "NO MATCH";
	}
	
	public void addVariable(VariableData v){
		variables.add(v);
	}
	
	public void addFunction(FunctionData f){
		functions.add(f);
	}
}
