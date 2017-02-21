package models;
import java.awt.Point;

import util.PolarAngle;
import util.RectAngle;

/**
 * Interface represents any actor displayed during
 * a simulation. A Turtle is an instance of an Actor.
 * 
 * Actor have many capabilities, and hold
 * knowledge about themselves that can be used for 
 * display.
 * 
 * @author maddiebriere
 *
 */

public interface Actor{
/**
Variety of getters and setters used to access 
Information in actors, for update and display
**/
	/**
	 * Point representing the current location of the
	 * actor
	 * @return Point location
	 */
	public Point getLocation(); //get location of actor
	public void setLocation(Point p); //set location of actor
	
	/**
	 * An int representing the speed of the
	 * actor as it updates on the screen
	 * @return
	 */
	public int getSpeed();
	public void setSpeed(int s);

	public double getSize();
	public void setSize(double d);

	/**
	 * Where PolarAngle is a util class
	 * @return angle in polar coordinates
	 */
	public PolarAngle getPolarAngle();
	public void setPolarAngle(PolarAngle p);

	/**
	 * Where RectAngle is a util class
	 * @return angle in rectangular coordinates
	 */
	public RectAngle getRectAngle();
	public void setRectAngle(RectAngle p);

	/**
	 * Visibility of actor on screen
	 * @return boolean visibility
	 */
	public boolean getVisible();
	public void setVisible(boolean b);

	/**
	 * Steps represent the remaining movement of the actor
	 * EX: If steps = 20, the actor has to take 20 steps
	*/
	public int getSteps();
	public void setSteps(int i);
}