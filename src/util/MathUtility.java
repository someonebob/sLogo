package util;

import javafx.geometry.Point2D;

/**
 * This class is a utility class containing static methods and constants for
 * mathematical capabilities throughout this project. Some examples include
 * converting between rectangular/polar coordinates and comparing doubles while
 * considering roundoff error.
 * 
 * @author Matthew Barbano
 *
 */
public final class MathUtility {
	public static final Point2D UNIT_X_VECTOR = new Point2D(1.0, 0.0);
	public static final Point2D UNIT_Y_VECTOR = new Point2D(0.0, 1.0);
	public static final double DOUBLE_COMPARISON_PRECISION = 0.000001;

	public static void main(String[] args) {
		System.out.println(Math.atan2(-1.0, 0.0));
		System.out.println(-Math.PI / 2);
	}

	/**
	 * Angle sorted in PointPolar is between -180 and 180 degrees.
	 * @param rectangularPoint
	 * @return
	 */
	public static PointPolar rectangularToPolar(Point2D rectangularPoint) {
		return new PointPolar(rectangularPoint.magnitude(),
				Math.toDegrees(Math.atan2(rectangularPoint.getY(), rectangularPoint.getX())));
	}

	public static Point2D polarToRectangular(PointPolar polarPoint) {
		return new Point2D(polarPoint.getDistance() * Math.cos(Math.toRadians(polarPoint.getAngle())),
				polarPoint.getDistance() * Math.sin(Math.toRadians(polarPoint.getAngle())));
	}

	public static boolean doubleEquals(double first, double second) {
		return Math.abs(first - second) < DOUBLE_COMPARISON_PRECISION;
	}

	public static boolean doubleLessThan(double first, double second) {
		return !doubleEquals(first, second) && first < second;
	}

	public static boolean doubleLessThanEquals(double first, double second) {
		return doubleEquals(first, second) || first < second;
	}

	public static boolean doubleGreaterThan(double first, double second) {
		return !doubleEquals(first, second) && first > second;
	}

	public static boolean doubleGreaterThanEquals(double first, double second) {
		return doubleEquals(first, second) || first > second;
	}
}
