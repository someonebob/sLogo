package models;
import java.util.List;
import javafx.scene.paint.Color;
/**
 * Interface representing environment in which actors are held
 * @author maddiebriere
 */
public abstract class Simulation{

	public Simulation(){
		//TODO: Implement default constructor
	}
	
	/**
 * Will mostly be composed of getters and setters, likely some
 * backend updating functionality
 */
	public abstract Color getColor();
	public abstract void setColor(Color c);
	
	public abstract List<Actor> getActors();
	public abstract void setActors(List<Actor> newActors);
	public abstract Actor getActiveActor();
	public abstract void setActiveActor(Actor newActor);
 //likely to add more to this as we develop program
}