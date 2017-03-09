package view;

import javafx.geometry.Point2D;
import javafx.scene.Node;
import main.Defaults;

public class TurtleView extends ActorView implements Cloneable
{

	private PenView pen;
	private Defaults defaults;

	public TurtleView(Defaults defaults, int id)
	{
		super(defaults, id);
		pen = new PenView(defaults.pen(), speed);
		this.defaults = defaults;
	}

	@Override
	public void step()
	{
		super.step();
		if (this.pen != null) {
			this.getPen().step();
		}
	}

	@Override
	public Node display()
	{
		return this.getImageView();
	}

	public PenView getPen()
	{
		return pen;
	}

	@Override
	public void setHeading(double newHeading)
	{
		super.setHeading(newHeading);
		if (pen != null) {
			pen.waitTransition(200);
		}
	}

	@Override
	public void move(Point2D newLocation)
	{
		pen.move(this.getLocation(), newLocation);
		super.move(newLocation);
	}

	@Override
	public TurtleView clone()
	{
		TurtleView clone = new TurtleView(defaults, this.getID().getID());
		clone.getImageProperty().setValue(this.getImageProperty().display());
		clone.setHeading(200);
		// clone.getImageColorProperty().setValue(this.getImageColorProperty().getValue());
		clone.getActorPositionProperty().setValue(this.getActorPositionProperty().getValue());
		return clone;
	}

}
