package view;

import javafx.geometry.Point2D;
import javafx.scene.Node;

public class TurtleView extends ActorView
{
	public static final String DEFAULT_IMAGE = "Default.png";

	private PenView pen;

	public TurtleView()
	{
		super(DEFAULT_IMAGE);
		pen = new PenView();
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
	public void move(Point2D newLocation)
	{
		pen.move(this.getActor().getLocation(), newLocation);
		super.move(newLocation);
	}
}
