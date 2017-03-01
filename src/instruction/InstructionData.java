package instruction;

import java.util.List;

import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import models.Actor;
import models.Simulation;
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
public class InstructionData {
	SimulationView simulation;
	
	public InstructionData(){
		//TODO: Implement default constructor
	}
	
	public InstructionData(SimulationView simulation){ //Add VariablePage here too
		this.simulation = simulation;
	}
	
	public Actor getActiveActor(){
		return simulation.getActiveActor();
	}
	
	public void setActiveActor(Actor activeActor){
		simulation.setActiveActor(activeActor);
	}
	
	/**
	 * Returns the List of Actors held by this class
	 * 
	 * @return
	 */
	public List<Actor> getActors(){
		return simulation.getActors();
	}

	/**
	 * Returns the background color of the current simulation
	 * 
	 * @return
	 */
	public Color getColor(){
		return simulation.getColor();
	}

	/**
	 * Sets the List of Actors in the current simulation to actorList
	 * 
	 * @param actorList
	 */
	public void setActors(List<Actor> actorList){
		simulation.setActors(actorList);
	}

	/**
	 * Sets the background color of the current simulation to color. Note that
	 * the implementation of this method not only updates the associated
	 * variable in this class, but also modifies the associated variable in
	 * Simulation and updates it.
	 * 
	 * @param color
	 */
	public void setColor(Color color){
		simulation.setColor(color);
	}

}
