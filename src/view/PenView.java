package view;

import java.util.Arrays;
import java.util.List;

import javafx.animation.PathTransition;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
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
import javafx.util.Duration;
import property.PenColorProperty;
import property.PenThicknessProperty;
import property.PenUpProperty;
import property.Property;
import property.SpeedProperty;

/**
 * 
 * @author jimmy
 *
 */
public class PenView implements View
{
	public static final DoubleProperty NUMERATOR = new SimpleDoubleProperty(1000);
	public static final double DEFAULT_FPS = 5;

	// private Path myPath;
	private Canvas canvas;
	private PenColorProperty color;
	private PenUpProperty penUp;
	private PenThicknessProperty penThickness;
	private SequentialTransition actorMove;
	private SpeedProperty speed;

	public PenView(Paint color, SpeedProperty speed)
	{
		canvas = new Canvas();
		this.color = new PenColorProperty("Pen color", canvas);
		this.color.setValue((Color) color);
		penUp = new PenUpProperty("Pen Up", this.color);
		penUp.setValue(false);
		penThickness = new PenThicknessProperty("Pen Thickness");
		penThickness.setValue(2.0);
		actorMove = new SequentialTransition();
		this.speed = speed;
	}

	public void step()
	{
		actorMove.play();
	}

	public void waitTransition(double waitTime)
	{

		PauseTransition delayTransition = new PauseTransition(Duration.millis(waitTime));

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
		Circle pen = new Circle(0, 0, penThickness.getValue());

		// create path transition
		PathTransition pathTransition = new PathTransition(Duration.millis(1000 / speed.getValue()), myPath, pen);

		pathTransition.currentTimeProperty().addListener(new ChangeListener<Duration>()
		{

			Point2D oldLocation = null;

			/**
			 * Draw a line from the old location to the new location
			 */
			@Override
			public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue)
			{
				System.out.println("hi");
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
				gc.setLineWidth(penThickness.getValue());
				gc.strokeLine(oldLocation.getX(), oldLocation.getY(), x + canvas.getWidth() / 2,
						y + canvas.getHeight() / 2);

				// update old location with current one
				oldLocation = new Point2D(x + canvas.getWidth() / 2, y + canvas.getHeight() / 2);
			}
		});
		actorMove.getChildren().add(pathTransition);
		pathTransition.setOnFinished(e -> {
			actorMove.getChildren().remove(pathTransition);
			gc.strokeLine(currLocation.getX() + canvas.getWidth() / 2, currLocation.getY() + canvas.getHeight() / 2,
					newLocation.getX() + canvas.getWidth() / 2, newLocation.getY() + canvas.getHeight() / 2);
		});
	}

	public Color getColor()
	{
		return color.getValue();
	}

	public void setColor(Color color)
	{
		this.color.setValue(color);
	}

	public void penUp()
	{
		this.penUp.setValue(true);
	}

	public void penDown()
	{
		this.penUp.setValue(false);
	}

	public boolean isUp()
	{
		return penUp.getValue();
	}

	public void clear()
	{
		canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
	}

	public Canvas getCanvas()
	{
		return canvas;
	}

	public double getThickness()
	{
		return penThickness.getValue();
	}

	public void setThickness(double thickness)
	{
		penThickness.setValue(thickness);
	}

	@Override
	public Node display()
	{
		return canvas;
	}

	public PenColorProperty getPenColorProperty()
	{
		return color;
	}

	public PenUpProperty getPenUpProperty()
	{
		return penUp;
	}

	public PenThicknessProperty getPenThicknessProperty()
	{
		return penThickness;
	}

	public List<Property<?>> getProperties()
	{
		return Arrays.asList(color, penUp, penThickness);
	}
}
