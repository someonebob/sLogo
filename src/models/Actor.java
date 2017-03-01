package models;

import javafx.geometry.Point2D;

/**
 * Interface represents any actor displayed during a simulation. A Turtle is an
 * instance of an Actor.
 * 
 * Actor have many capabilities, and hold knowledge about themselves that can be
 * used for display.
 * 
 * @author maddiebriere
 * @author Matthew Barbano
 *
 */

public class Actor
{
	/**
	 * Variety of getters and setters used to access Information in actors, for
	 * update and display
	 **/
	private Point2D location;
	private double speed;
	private double heading;

	public Actor()
	{
		this(new Point2D(100, 100), 0);
	}

	public Actor(Point2D location, double speed)
	{
		this.location = location;
		this.speed = speed;
	}

	/**
	 * Point representing the current location of the actor
	 * 
	 * @return Point location
	 */
	public Point2D getLocation()
	{
		return location;
	}

	public void setLocation(Point2D p)
	{
		location = p;
	}

	/**
	 * An int representing the speed of the actor as it updates on the screen
	 * 
	 * @return
	 */
	public double getSpeed()
	{
		return speed;
	}

	public void setSpeed(double s)
	{
		speed = s;
	}

	public double getHeading()
	{
		return heading;
	}

	public void setHeading(double heading)
	{
		this.heading = heading;
	}

	/**
	 * Steps represent the remaining movement of the actor EX: If steps = 20,
	 * the actor has to take 20 steps
	 */
	public int getSteps()
	{
		// TODO
		return -1;
	}

	public void setSteps(int i)
	{
		// TODO
	}
}