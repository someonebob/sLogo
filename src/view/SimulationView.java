package view;


import java.util.List;

import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import property.BackgroundColorProperty;
import property.Property;

public interface SimulationView extends View{
	public void step();
	public void move(Point2D deltaLocation);
	public void setBackgroundColor(String color);
	public TurtleView getTurtle();
	public void newActor();
	public Bounds getBounds();
	public BackgroundColorProperty getBackgroundColorProperty();
	public List<Property<?>> getProperties();

}
