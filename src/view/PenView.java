package view;

import java.util.Observable;

import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.FillRule;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

/**
 * 
 * @author jimmy
 *
 */
public class PenView implements View
{
	private Path myPath;
	private double thickness;

	public PenView()
	{
		thickness = 2;
		myPath = new Path();
		MoveTo initialPosition = new MoveTo(0, 0);
		myPath.getElements().add(initialPosition);
	}

	public void move(Point2D newLocation)
	{
		LineTo lineTo = new LineTo();
		lineTo.setX(newLocation.getX());
		lineTo.setY(newLocation.getY());
		myPath.setStroke(Color.BLACK);
		myPath.setStrokeWidth(thickness);
		myPath.setFillRule(FillRule.EVEN_ODD);
		myPath.getElements().add(lineTo);
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

	@Override
	public Node display()
	{
		return myPath;
	}
}
