package view;

import java.io.File;

import javafx.geometry.Point2D;
import main.Defaults;
import user_structures.NamedImageWrapper;

public class TurtleView extends ActorView
{
	private static final String TURTLE_IMAGES_LOCATION = "images";
	private PenView pen;
	
	public TurtleView(Defaults defaults, int id)
	{
		super(defaults, id);
		pen = new PenView(defaults.pen());
	}

	@Override
	public void step()
	{
		super.step();
		this.getPen().step();
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

	//TODO Possibly modify for more modular file reading
	@Override
	protected void populateAvailableImages() {
		File currentFolder = new File(".");
		File srcFolder = currentFolder.getParentFile();
		File imagesFolder = new File(srcFolder, TURTLE_IMAGES_LOCATION);
		for(File imageFile : imagesFolder.listFiles()){
			getAvailableImages().add(new NamedImageWrapper(imageFile.getName()));
		}
	}
}
