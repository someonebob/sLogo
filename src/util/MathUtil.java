package util;

import javafx.geometry.Point2D;

/**
 * This class is a utility class containing static methods and constants for
 * mathematical capabilities throughout this project. Some examples include
 * converting between rectangular/polar coordinates and comparing doubles while
 * considering roundoff error. The class is final so no subclasses can extend
 * it, and it has a private no-arg constructor so the default public no-arg
 * constructor is not accidentally called.
 * 
 * @author Matthew Barbano
 * @author jimmy
 *
 */
public final class MathUtil
{
	public static final Point2D UNIT_X_VECTOR = new Point2D(1.0, 0.0);
	public static final Point2D UNIT_Y_VECTOR = new Point2D(0.0, 1.0);
	public static final double DOUBLE_COMPARISON_PRECISION = 0.000001;

	private MathUtil()
	{
		// This constructor body is intentionally left blank.
	}

	/**
	 * Angle sorted in PointPolar is between -180 and 180 degrees.
	 * 
	 * @param rectangularPoint
	 * @return
	 */
	public static PointPolar rectangularToPolar(Point2D rectangularPoint)
	{
		return new PointPolar(rectangularPoint.magnitude(),
				Math.toDegrees(Math.atan2(rectangularPoint.getY(), rectangularPoint.getX())));
	}

	public static Point2D polarToRectangular(PointPolar polarPoint)
	{
		return new Point2D(polarPoint.getDistance() * Math.cos(Math.toRadians(polarPoint.getAngle())),
				polarPoint.getDistance() * Math.sin(Math.toRadians(polarPoint.getAngle())));
	}

	public static boolean doubleEquals(double first, double second)
	{
		return Math.abs(first - second) < DOUBLE_COMPARISON_PRECISION;
	}

	public static boolean doubleLessThan(double first, double second)
	{
		return !doubleEquals(first, second) && first < second;
	}

	public static boolean doubleLessThanEquals(double first, double second)
	{
		return doubleEquals(first, second) || first < second;
	}

	public static boolean doubleGreaterThan(double first, double second)
	{
		return !doubleEquals(first, second) && first > second;
	}

	public static boolean doubleGreaterThanEquals(double first, double second)
	{
		return doubleEquals(first, second) || first > second;
	}
<<<<<<< src/util/MathUtil.java
	
	public static boolean hasIntegerValue(double candidateDouble){
		return MathUtil.doubleEquals(candidateDouble, Math.round(candidateDouble));
=======

	public static boolean hasIntegerValue(double candidateDouble)
	{
		return MathUtil.doubleEquals(candidateDouble, Math.round(candidateDouble));
	}

	public static double distance(double x, double y)
	{
		return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
>>>>>>> src/util/MathUtil.java
	}
}
