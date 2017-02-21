package models;

import java.util.List;

import javafx.scene.paint.Color;

/**
 * Interface representing environment in which actors are held
 * @author maddiebriere
 */


public interface Simulation{
/**
 * Will mostly be composed of getters and setters, likely some
 * backend updating functionality
 */

	public Color getColor();
	public void setColor(Color c);
	
	public List<Actor> getActors();

 //likely to add more to this as we develop program
}