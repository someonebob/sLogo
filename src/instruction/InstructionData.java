package instruction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import exceptions.InvalidIndexException;
import exceptions.SLogoException;
import interpreter.util.WorkspaceUpdater;
import javafx.geometry.Bounds;
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
	SimulationView simulationView;
	List<VariableData> variables;
	List<FunctionData> functions;
	String language;

	public InstructionData()
	{
		this.simulationView = null;
		this.variables = new ArrayList<VariableData>();
		this.functions = new ArrayList<FunctionData>();
		this.language = "English";
	}

	public InstructionData(SimulationView simulation, List<VariableData> variables, List<FunctionData> functions,
			String language)
	{
		this.simulationView = simulation;
		this.variables = variables;
		this.functions = functions;
		this.language = language;
	}

	public TurtleView getActiveActor()
	{
		return simulationView.getTurtle();
	}

	/**
	 * Returns the List of Actors held by this class
	 * 
	 * @return
	 */
	public List<ActorView> getActors()
	{
		return Arrays.asList(simulationView.getTurtle());
	}

	public Bounds getSimulationBounds()
	{
		return simulationView.getBounds();
	}

	public List<VariableData> getVariables()
	{
		return variables;
	}

	public List<FunctionData> getFunctions()
	{
		return functions;
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

	public String getVariableValue(String variableName)
	{
		for (VariableData v : variables) {
			if (v.getName().equals(variableName)) {
				return "" + v.getValue();
			}
		}
		return "NO MATCH";
	}

	public String getFunctionValue(String functionName)
	{
		for (FunctionData f : functions) {
			if (f.getName().equals(functionName)) {
				return f.getCommands();
			}
		}
		return "NO MATCH";
	}

	public void addVariable(VariableData v)
	{
		WorkspaceUpdater.add(variables, v);
	}

	public void addFunction(FunctionData f)
	{
		WorkspaceUpdater.add(functions, f);
	}

	public String getLanguage()
	{
		return language;
	}

	public SimulationView getSimulation()
	{
		return simulationView;
	}
	
	public PenView getActivePenView(){
		if(!(getActiveActor() instanceof TurtleView)){
			throw new SLogoException("Placeholder");  //TODO Change to subclass of exception
		}
		return ((TurtleView) getActiveActor()).getPen();
	}
	/*
	public List<Color> getColorList(){
		return null;//simulationView.getColorList();
	}
	public List<NamedImageWrapper> getTurtleImageList(){
		return getActiveActor().getAvailableImages();
	}
	*/
	/*
	public void setImageByIndex(double index){
		checkValidIndex(index, getTurtleImageList().size());
		getActiveActor().setImage(getTurtleImageList().get((int)index));
	}
	*/
	/**
	 * (Current actor's imageView's index)
	 * @return
	 */
	/*
	public int getImageByIndex(){
		int index = getTurtleImageList().indexOf(getActiveActor().getImage());
		if(index != -1){
			return index;
		}
		else{
			throw new InvalidIndexException(RESOURCE_NOT_FOUND_NAME);
		}
	}
	
	private void checkValidIndex(double index, int size){
		if(!MathUtil.hasIntegerValue(index)){
			throw new InvalidIndexException(RESOURCE_DOUBLE_NAME);
		}
		if(index < 0.0 || index >= size){
			throw new InvalidIndexException(RESOURCE_BOUNDS_NAME);
		}
	}
	*/
}