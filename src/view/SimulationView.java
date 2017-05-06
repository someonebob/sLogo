package view;

import java.util.Collection;
import java.util.List;
import java.util.Observer;

import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;
import property.BackgroundColorProperty;
import property.Property;

/**
 * Interface to be implemented by Simulations, if we ever want to have different
 * kinds of SimulationViews For example animated vs unanimated
 * 
 * @author Jesse
 *
 */
public interface SimulationView extends View, Observer
{
	/**
	 * Undo last command
	 */
	public void undo();

	/**
	 * Tells the current actors to actually move
	 */
	public void step();

	/**
	 * Tells the current actors what location to move to
	 * 
	 * @param deltaLocation
	 */
	public void move(Point2D deltaLocation);

	/**
	 * Sets the background color of the simulation
	 * 
	 * @param color
	 */
	public void setBackgroundColor(String color);

	/**
	 * Allows user to choose which turtles to be active
	 * 
	 * @param toldTurtles
	 */
	public void setTold(Collection<Integer> toldTurtles);

	/**
	 * 
	 * @return list of all actors
	 */
	public List<TurtleView> getActors();

	/**
	 * Gets the first turtle
	 * 
	 * @return
	 */
	public TurtleView getTurtle();

	/**
	 * returns the bounds of the simulation
	 * 
	 * @return
	 */
	public Bounds getBounds();

	/**
	 * 
	 * @returns the BackgroundColorProperty
	 */
	public BackgroundColorProperty getBackgroundColorProperty();

	/**
	 * 
	 * @returns a list of all properties
	 */
	public List<Property<?>> getProperties();

	/**
	 * Sets the simulation to an existing simulation
	 * 
	 * @param simulation
	 */
	public void set(SimulationView simulation);

	/**
	 * Creates a new actor in the simulation
	 */
	public void newActor();
	
	/**
	 * Draws stamp on screen with same positioning and heading as "stamp"
	 * @param stamp
	 */
	public abstract void drawStamp(ActorView stamp);
	
	/**
	 * Returns a list of image views representing the current stamps on screen.
	 */
	public abstract List<ImageView> getStamps();
	
	/**
	 * Removes all stamps from the "stamps" list and removed them
	 * from the root StackPane so they disappear from screen.
	 */
	public abstract void clearStamps();
}