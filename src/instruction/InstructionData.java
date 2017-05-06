package instruction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import exceptions.CastingException;
import interpreter.util.WorkspaceUpdaterUtil;
import javafx.geometry.Bounds;
import property.BackgroundColorProperty;
import user_structures.FunctionData;
import user_structures.VariableData;
import view.ActorView;
import view.PenView;
import view.SimulationView;
import view.TurtleView;

/**
 * This class is part of the Backend Internal API.
 * 
 * <p>
 * This acts as container for all of the data associated with the current state
 * of the simulation. It is used by the Instruction hierarchy to access and
 * modify different elements of the simulation - for example, actors' positions
 * and headings.
 * </p>
 * 
 * <p>
 * It bundles together information about the simulation (SimulationView object),
 * variables, and functions currently present, which are located in various
 * locations on the frontend so the Instruction hierarchy can access this
 * information in a single place, but it does not allow the hierarchy to
 * directly access the simulation to prevent it from calling methods it should
 * not access (i.e. there is no getSimulationView()). This class also keeps
 * track of the currently "active" Actor.
 * </p>
 * 
 * <p>
 * It is assumed that none of the private instance variables are null (i.e. the
 * default constructor should only be used for testing), and dependencies
 * include String, SimulationView, List, VariableData, and FunctionData.
 * </p>
 * 
 * @author Matthew Barbano
 * @author maddiebriere
 *
 */

public class InstructionData {
	private static final String RESOURCE_CAST_EXCEPTION = "CastingMessage";
	SimulationView simulationView;
	List<VariableData> variables;
	List<FunctionData> functions;
	String language;
	int activeActorIndex;

	/**
	 * 0-argument constructor which sets simulationView to null, the variable
	 * and function lists to empty ArrayLists, language to "English", and
	 * activeActorIndex to 0. Assumes this constructor used only for testing
	 * purposes; call the other constructor otherwise. Dependencies are
	 * SimulatioView, VariableData, FunctionData, List, and String.
	 */
	public InstructionData() {
		this.simulationView = null;
		this.variables = new ArrayList<VariableData>();
		this.functions = new ArrayList<FunctionData>();
		this.language = "English";
		this.activeActorIndex = 0;
	}

	/**
	 * The constructor which should be used to instantiate an InstructionData
	 * object. Sets corresponding variables to parameters given and
	 * activeActorIndex to 0. Assumes none of the parameters are null, and
	 * dependencies are SimulatioView, VariableData, FunctionData, List, and
	 * String.
	 * 
	 * @param simulation
	 *            the SimulationView stored in this instance
	 * @param variables
	 *            the List of all existing variables
	 * @param functions
	 *            the List of all existing functions
	 * @param language
	 *            the current language of the simulation
	 */
	public InstructionData(SimulationView simulation, List<VariableData> variables, List<FunctionData> functions,
			String language) {
		this.simulationView = simulation;
		this.variables = variables;
		this.functions = functions;
		this.language = language;
		this.activeActorIndex = 0;
	}

	/**
	 * Returns the active actor index. No assumptions or dependencies.
	 * 
	 * @return activeActorIndex
	 */
	public int getActiveActorIndex() {
		return activeActorIndex;
	}

	/**
	 * Returns the ObservableList of TurtleViews held by simulationView. No
	 * assumptions, dependency is simulationView.
	 * 
	 * @return the List of TurtleView actors
	 */
	public List<TurtleView> getActorList() {
		return simulationView.getActors();
	}

	/**
	 * Returns the BackgroundColorProperty held in simulationView which keeps
	 * track of the simulationView's background color. No assumptions,
	 * dependencies are SimulationView and BackgroundColorProperty.
	 * 
	 * @return the background color
	 */
	public BackgroundColorProperty getBackgroundColorProperty() {
		return simulationView.getBackgroundColorProperty();
	}

	/**
	 * Creates a new actor in simulationView, adds it to the ObservableList
	 * "actors", and takes care of all graphics/frontend tasks associated with
	 * adding a new actor. Assumes the new actor is to be added at the end of
	 * the current ObservableList called "actors", dependency is simulationView.
	 */
	public void newActor() {
		simulationView.newActor();
	}

	/**
	 * Sets all turtles whose ids are specified in "toldTurtles" to "told" and
	 * makes all other existing turtles "untold." Assumes toldTurtles contains
	 * valid ids. Dependencies are simulationView, Collection, and Integer.
	 * 
	 * @param toldTurtles
	 *            the subset of turtles to set to "told"
	 */
	public void setToldAndUntellRest(Collection<Integer> toldTurtles) {
		simulationView.setTold(toldTurtles);
	}

	/**
	 * Returns a new instance of InstructionData with all of the same fields as
	 * this instance of InstructionData, except for the List of variables, which
	 * is specified by the parameter. Called in the UserInstruction class. No
	 * assumptions, and dependencies are the same as those in InstructionData's
	 * constructors.
	 * 
	 * @param newVariableList
	 *            the new list of variables
	 * @return the copy of itself with the new list of variables
	 */
	public InstructionData replicateSelfWithNewVariables(List<VariableData> newVariableList) {
		return new InstructionData(simulationView, newVariableList, functions, language);
	}

	/**
	 * Returns the "active" ActorView, as opposed to just the activeActorIndex
	 * (for better encapsulation). Assumes activeActorIndex is a valid actor id.
	 * Dependencies are SimulationView, TurtleView, and List.
	 * 
	 * @return
	 */
	public ActorView getActiveActor() {
		return simulationView.getActors().get(activeActorIndex);
	}

	/**
	 * Sets the activeActorIndex to newIndex. No assumptions or dependencies.
	 * 
	 * @param newIndex
	 *            the new active actor index
	 */
	public void setActiveActorIndex(int newIndex) {
		activeActorIndex = newIndex;
	}

	/**
	 * Accesses the Bounds object from simulationView representing the borders
	 * of the simulation graphic. Assumes simulationView is not null;
	 * dependencies are SimulationView and Bounds.
	 * 
	 * @return the Bounds representing the borders
	 */
	public Bounds getSimulationBounds() {
		return simulationView.getBounds();
	}

	/**
	 * Returns "variables" field. No assumptions, List and VariableData are
	 * dependencies.
	 * 
	 * @return variables
	 */
	public List<VariableData> getVariables() {
		return variables;
	}

	/**
	 * Returns a List of VariableData objects representing those variables on
	 * the stack in the VariableData class, destroying the stack in the process.
	 * Used for dynamic scoping, recursion, etc. No assupmtions, dependencies
	 * are List and VariableData.
	 * 
	 * @return the List of variables initially on the stack
	 */
	public List<VariableData> getStackVariables() {
		for (int i = 0; i < variables.size(); i++) {
			VariableData v = variables.get(i);
			if (v.getStackSize() != 0)
				v.popFromStack(); // iterate through and pop items
			else {
				variables.remove(v);
			}
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
	public VariableData getVariable(String variableName) {
		for (VariableData v : variables) {
			if (v.getName().equals(variableName)) {
				return v;
			}
		}
		return null;
	}

	/**
	 * Returns true if the variable whose String representation is
	 * "variableName" is in the workspace. Assumes variableName is not null.
	 * VariableData is dependency.
	 * 
	 * @param variableName
	 *            the queried variable
	 * @return true if variable exists
	 */
	public boolean containsVariable(String variableName) {
		return !(getVariable(variableName) == null);
	}

	/**
	 * Checks if the current workspace contains a function of the name given and
	 * returns that function if it does
	 * 
	 * @param functionName
	 *            The potential function name
	 * @return Function matching to the current name, otherwise null
	 */
	public FunctionData getFunction(String functionName) {
		for (FunctionData f : functions) {
			if (f.getName().equals(functionName)) {
				return f;
			}
		}
		return null;
	}

	/**
	 * Returns true if the function with String representation functionName
	 * exists in the workspace. Assumes functionName is not null. Dependency is
	 * FunctionData.
	 * 
	 * @param functionName
	 *            the queried function
	 * @return true if the function exists
	 */
	public boolean containsFunction(String functionName) {
		return !(getFunction(functionName) == null);
	}

	/**
	 * Adds the function f to the workspace. Assumes f is not null,
	 * WorkspaceUpdaterUtil and FunctionData are dependencies.
	 * 
	 * @param f
	 *            the function to be added
	 */
	public void addFunction(FunctionData f) {
		WorkspaceUpdaterUtil.add(functions, f);
	}

	/**
	 * Returns the currently active language for resource file reading. No
	 * assumptions, String is dependency.
	 * 
	 * @return language
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * Returns the active actor's PenView. Assumes the active actor is an
	 * instance of TurtleView; throws a CastingException otherwise. Dependencies
	 * are PenView, TurtleView, ActorView, and CastingException.
	 * 
	 * @return the active actor's pen view
	 * @throws CastingException
	 *             if active actor not a TurtleView
	 */
	public PenView getActivePenView() {
		if (!(getActiveActor() instanceof TurtleView)) {
			throw new CastingException(RESOURCE_CAST_EXCEPTION);
		}
		return ((TurtleView) getActiveActor()).getPen();
	}
	
	public List<TurtleView> getStamps(){
		return simulationView.getStamps();
	}
	
	public void drawStamp(TurtleView stamp){
		simulationView.drawStamp(stamp);
	}

}