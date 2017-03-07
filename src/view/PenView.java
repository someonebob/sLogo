package view;

import java.util.Observable;

import javafx.animation.FadeTransition;
import javafx.animation.PathTransition;
import javafx.animation.SequentialTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
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
	private Color color;
	private boolean isUp;
	private SequentialTransition actorMove;

	public PenView(Paint color)
	{
		isUp = false;
		thickness = 2;
		this.color = (Color) color;
		canvas = new Canvas();
		this.setColor(this.color);
		actorMove = new SequentialTransition();
	}

	public void step()
	{
		actorMove.play();
	}

	public void waitTransition(double waitTime)
	{
		FadeTransition delayTransition = new FadeTransition(Duration.millis(waitTime), new Rectangle());
		actorMove.getChildren().add(delayTransition);
		delayTransition.setOnFinished(e -> {
			actorMove.getChildren().remove(delayTransition);
		});
	}

	public void move(Point2D currLocation, Point2D newLocation)
	{
		Path myPath = new Path();
		MoveTo initialPosition = new MoveTo(currLocation.getX(), currLocation.getY());
		LineTo lineTo = new LineTo();
		lineTo.setX(newLocation.getX());
		lineTo.setY(newLocation.getY());
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
				gc.setLineWidth(thickness);
				gc.strokeLine(oldLocation.getX(), oldLocation.getY(), x + canvas.getWidth() / 2,
						y + canvas.getHeight() / 2);

				// update old location with current one
				oldLocation = new Point2D(x + canvas.getWidth() / 2, y + canvas.getHeight() / 2);
			}
		});
		actorMove.getChildren().add(pathTransition);
		pathTransition.setOnFinished(e -> {
			actorMove.getChildren().remove(pathTransition);
		});
	}

	public void setColor(Color color)
	{
		canvas.getGraphicsContext2D().setFill(color);
		canvas.getGraphicsContext2D().setStroke(color);
		this.color = color;
	}

	@Override
	public void update(Observable o, Object arg)
	{

	}

	public void penUp()
	{
		this.setColor(Color.TRANSPARENT);
		this.isUp = true;
	}

	public void penDown()
	{
		this.setColor(color);
		this.isUp = false;
	}

	public boolean isUp()
	{
		return isUp;
	}

	public void clear()
	{
		canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
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
