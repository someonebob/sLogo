package instruction;

import java.util.Arrays;
import java.util.List;

import javafx.geometry.Bounds;
import models.Variable;
import view.ActorView;
import view.SimulationView;

/**
 * This acts as container for all of the data associated with the current state
 * of the simulation. It is used by Interpreter's subclasses to access and
 * modify different elements of the simulation - for example, actors' positions
 * and headings.
 * 
 * @author Matthew Barbano
 *
 */
public class InstructionData
{
	SimulationView simulation;

	public InstructionData()
	{
		// TODO: Implement default constructor
	}

	public InstructionData(SimulationView simulation, List<Variable> variables)
	{ // Add VariablePage here too
		this.simulation = simulation;
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
}
