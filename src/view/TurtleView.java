package view;

import javafx.geometry.Point2D;
import javafx.scene.Node;

public class TurtleView extends ActorView
{
	public static final String DEFAULT_IMAGE = "Default.png";

	private PenView pen;

	public TurtleView(int id)
	{
		super(DEFAULT_IMAGE, id);
		pen = new PenView();
	}

	@Override
	public void step()
	{
		super.step();
		this.getPen().step();
	}

	@Override
	public Node display()
	{
		return this.getImage();
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
		pen.move(this.getActor().getLocation(), newLocation);
		super.move(newLocation);
	}
}
