package view;

import javafx.scene.Node;

public class TurtleView extends ActorView
{

	public static final String DEFAULT_IMAGE = "Squirt.png";

	public TurtleView()
	{
		super(DEFAULT_IMAGE);
	}

	@Override
	public Node display()
	{
		return this.getImage();
	}

}
