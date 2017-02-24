package models;

import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;

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

public class Actor {
	/**
	 * Variety of getters and setters used to access Information in actors, for
	 * update and display
	 **/
	private Point2D location;
	private double speed;
	private double size;
	private double heading;
	private ImageView turtleImage;
	private boolean visible;

	public Actor(Point2D location, double speed, double size, double heading, ImageView turtleImage, boolean visible) {
		this.location = location;
		this.speed = speed;
		this.size = size;
		this.heading = heading;
		this.turtleImage = turtleImage;
		this.visible = visible;
	}

	/**
	 * Point representing the current location of the actor
	 * 
	 * @return Point location
	 */
	public Point2D getLocation() {
		return location;
	}

	public void setLocation(Point2D p) {
		location = p;
	}

	/**
	 * An int representing the speed of the actor as it updates on the screen
	 * 
	 * @return
	 */
	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double s) {
		speed = s;
	}

	public double getSize() {
		return size;
	}

	public void setSize(double d) {
		size = d;
	}

	public double getHeading() {
		return heading;
	}

	public void setHeading(double d) {
		heading = d;
	}

	public ImageView getTurtleImage() {
		return turtleImage;
	}

	public void setTurtleImage(ImageView newImage) {
		turtleImage = newImage;
	}

	/**
	 * Visibility of actor on screen
	 * 
	 * @return boolean visibility
	 */
	public boolean getVisible() {
		return visible;
	}

	public void setVisible(boolean b) {
		visible = b;
	}

	/**
	 * Steps represent the remaining movement of the actor EX: If steps = 20,
	 * the actor has to take 20 steps
	 */
	public int getSteps() {
		// TODO
		return -1;
	}

	public void setSteps(int i) {
		// TODO
	}
}