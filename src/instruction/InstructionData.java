package instruction;

import java.util.List;

import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import models.Actor;

/**
 * This acts as container for all of the data associated with the current state
 * of the simulation. It is used by Interpreter's subclasses to access and
 * modify different elements of the simulation - for example, actors' positions
 * and headings.
 * 
 * @author Matthew Barbano
 *
 */
public interface InstructionData {
	/**
	 * Returns the List of Actors held by this class
	 * 
	 * @return
	 */
	public List<Actor> getActorList();

	/**
	 * Returns the background color of the current simulation
	 * 
	 * @return
	 */
	public Color getBackgroundColor();

	/**
	 * Returns the ImageView used for Turtles in the current simulation
	 * 
	 * @return
	 */
	public ImageView getTurtleImage();

	/**
	 * Sets the List of Actors in the current simulation to actorList
	 * 
	 * @param actorList
	 */
	public void setActorList(List<Actor> actorList);

	/**
	 * Sets the background color of the current simulation to color. Note that
	 * the implementation of this method not only updates the associated
	 * variable in this class, but also modifies the associated variable in
	 * Simulation and updates it.
	 * 
	 * @param color
	 */
	public void setBackgroundColor(Color color);

	/**
	 * Sets the ImageView used to display turtles to imageView. Note that the
	 * implementation of this method not only updates the associated variable in
	 * this class, but also modifies the associated variable in Simulation and
	 * updates it.
	 * 
	 * @param imageView
	 */
	public void setTurtleImage(ImageView imageView);
}
