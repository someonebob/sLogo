package instruction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import exceptions.CastingException;
import interpreter.util.WorkspaceUpdater;
import javafx.geometry.Bounds;
import property.BackgroundColorProperty;
import user_structures.FunctionData;
import user_structures.VariableData;
import view.ActorView;
import view.PenView;
import view.SimulationView;
import view.TurtleView;

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
	private static final String RESOURCE_CAST_EXCEPTION = "CastingMessage";
	SimulationView simulationView;
	List<VariableData> variables;
	List<FunctionData> functions;
	String language;
	int activeActorIndex;

	public InstructionData()
	{
		this.simulationView = null;
		this.variables = new ArrayList<VariableData>();
		this.functions = new ArrayList<FunctionData>();
		this.language = "English";
		this.activeActorIndex = 0;
	}

	public InstructionData(SimulationView simulation, List<VariableData> variables, List<FunctionData> functions,
			String language)
	{
		this.simulationView = simulation;
		this.variables = variables;
		this.functions = functions;
		this.language = language;
		this.activeActorIndex = 0;
	}

	/**
	 * Returns the List of Actors held by this class
	 * 
	 * @return
	 */
	public List<TurtleView> getActorList()
	{
		return simulationView.getActors();
	}
	
	public BackgroundColorProperty getBackgroundColorProperty(){
		return simulationView.getBackgroundColorProperty();
	}
	
	public void newActor(){
		simulationView.newActor();
	}
	
	public void setToldAndUntellRest(Collection<Integer> toldTurtles){
		simulationView.setTold(toldTurtles);
	}
	
	public InstructionData replicateSelfWithNewVariables(List<VariableData> newVariableList){
		return new InstructionData(simulationView, newVariableList, functions, language);
	}
	
	public ActorView getActiveActor()
	{
		return simulationView.getActors().get(activeActorIndex);
	}
	
	public void setActiveActorIndex(int newIndex){
		activeActorIndex = newIndex;
	}

	public Bounds getSimulationBounds()
	{
		return simulationView.getBounds();
	}

	public List<VariableData> getVariables()
	{
		return variables;
	}

	public List<VariableData> getStackVariables()
	{
		for (VariableData v : variables) {
			if (v.getStackSize() != 0)
				v.popFromStack(); // iterate through and pop items
		}
		return variables;
	}

	/**
	 * Checks if the current workspace contains a variable of the name given and
	 * returns that variable if it does
	 * 
	 * @param variableName
	 *            The potential variable name
	 * @return Variable matching to the current name, otherwise null
	 */
	public VariableData containsVariable(String variableName)
	{
		for (VariableData v : variables) {
			if (v.getName().equals(variableName)) {
				return v;
			}
		}
		return null;
	}

	/**
	 * Checks if the current workspace contains a function of the name given and
	 * returns that function if it does
	 * 
	 * @param functionName
	 *            The potential function name
	 * @return Function matching to the current name, otherwise null
	 */
	public FunctionData containsFunction(String functionName)
	{
		for (FunctionData f : functions) {
			if (f.getName().equals(functionName)) {
				return f;
			}
		}
		return null;
	}

	public void addFunction(FunctionData f)
	{
		WorkspaceUpdater.add(functions, f);
	}

	public String getLanguage()
	{
		return language;
	}
	
	public PenView getActivePenView(){
		if(!(getActiveActor() instanceof TurtleView)){
			throw new CastingException(RESOURCE_CAST_EXCEPTION);
		}
		return ((TurtleView) getActiveActor()).getPen();
	}

}