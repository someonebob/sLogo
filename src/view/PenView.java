package view;

import java.util.Observable;

import javafx.animation.PathTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.FillRule;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

/**
 * 
 * @author jimmy
 *
 */
public class PenView implements View
{
	// private Path myPath;
	private Canvas canvas;
	private double thickness;

	public PenView()
	{
		thickness = 2;
		// myPath = new Path();
		canvas = new Canvas();
		canvas.getGraphicsContext2D().setFill(Color.AQUA);
		MoveTo initialPosition = new MoveTo(0, 0);
		// myPath.getElements().add(initialPosition);
	}

	public void move(Point2D currLocation, Point2D newLocation)
	{
		Path myPath = new Path();
		MoveTo initialPosition = new MoveTo(currLocation.getX(), currLocation.getY());
		LineTo lineTo = new LineTo();
		lineTo.setX(newLocation.getX());
		lineTo.setY(newLocation.getY());
		myPath.setStroke(Color.BLACK);
		myPath.setStrokeWidth(thickness);
		myPath.setFillRule(FillRule.EVEN_ODD);
		myPath.getElements().add(initialPosition);
		myPath.getElements().add(lineTo);

		GraphicsContext gc = canvas.getGraphicsContext2D();
		Circle pen = new Circle(0, 0, thickness);

		// create path transition
		PathTransition pathTransition = new PathTransition(Duration.millis(500), myPath, pen);
		pathTransition.currentTimeProperty().addListener(new ChangeListener<Duration>()
		{

			Point2D oldLocation = null;

			/**
			 * Draw a line from the old location to the new location
			 */
			@Override
			public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue)
			{
				// skip starting at 0/0
				if (oldValue == Duration.ZERO)
					return;

				// get current location
				double x = pen.getTranslateX();
				double y = pen.getTranslateY();

				// initialize the location
				if (oldLocation == null) {
					oldLocation = new Point2D(x + canvas.getWidth() / 2, y + canvas.getHeight() / 2);
					return;
				}
				// draw line
				gc.setStroke(Color.BLUE);
				gc.setFill(Color.YELLOW);
				gc.setLineWidth(4);
				gc.strokeLine(oldLocation.getX(), oldLocation.getY(), x + canvas.getWidth() / 2,
						y + canvas.getHeight() / 2);

				// update old location with current one
				oldLocation = new Point2D(x + canvas.getWidth() / 2, y + canvas.getHeight() / 2);
			}
		});
		pathTransition.play();
	}

	@Override
	public void update(Observable o, Object arg)
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void updateData(String arg)
	{
		// TODO Auto-generated method stub

	}

	public Canvas getCanvas()
	{
		return canvas;
	}

	@Override
	public Node display()
	{
		return canvas;
	}
}
