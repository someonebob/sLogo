package util;

import exceptions.InvalidIndexException;
import javafx.geometry.Point2D;

/**
 * This class, which follows the Utility Class Design Pattern, contains static
 * methods and constants for mathematical capabilities throughout this project.
 * Some examples include converting between rectangular/polar coordinates and
 * comparing doubles while considering roundoff error. Since the class is a
 * Utility class, it is final so no subclasses can extend it, and it has a
 * private no-argument constructor so the default public no-argument constructor
 * is not accidentally called. Assumes all angles are in degrees. Dependencies
 * are Point2D, String, java.lang.Math, InvalidIndexException, and the helper
 * class PointPolar.
 * 
 * @author Matthew Barbano
 * @author jimmy
 *
 */
public final class MathUtil {
	public static final Point2D UNIT_X_VECTOR = new Point2D(1.0, 0.0);
	public static final Point2D UNIT_Y_VECTOR = new Point2D(0.0, 1.0);
	public static final double DOUBLE_COMPARISON_PRECISION = 0.000001;
	private static final String RESOURCE_DOUBLE_NAME = "DoubleIndexMessage";
	private static final String RESOURCE_BOUNDS_NAME = "IndexOutOfBoundsMessage";

	/**
	 * A blank private constructor replacing Java's automatically generated
	 * public no-argument constructor so no instances of the MathUtil class can
	 * be created from other classes.
	 */
	private MathUtil() {
		// This constructor body is intentionally left blank.
	}

	/**
	 * Converts rectangularPoint to polar coordinates. Resulting angle sorted in
	 * PointPolar is between -180 and 180 degrees. No assumptions; dependencies
	 * are PointPolar and Math.
	 * 
	 * @param rectangularPoint
	 * @return the point in polar
	 */
	public static PointPolar rectangularToPolar(Point2D rectangularPoint) {
		return new PointPolar(rectangularPoint.magnitude(),
				Math.toDegrees(Math.atan2(rectangularPoint.getY(), rectangularPoint.getX())));
	}

	/**
	 * Converts polarPoint to rectangular coordinates. Assumes polarPoint stores
	 * angle in degrees. Dependencies are Point2D and Math.
	 * 
	 * @param polarPoint
	 * @return the point in rectangular
	 */
	public static Point2D polarToRectangular(PointPolar polarPoint) {
		return new Point2D(polarPoint.getDistance() * Math.cos(Math.toRadians(polarPoint.getAngle())),
				polarPoint.getDistance() * Math.sin(Math.toRadians(polarPoint.getAngle())));
	}

	/**
	 * Compares two doubles safely for equality by seeing if their positive
	 * difference is smaller than DOUBLE_COMPARISON_PRECISION. No assumptions;
	 * Math is dependency.
	 * 
	 * @param first
	 * @param second
	 * @return true if first == second, compared safely
	 */
	public static boolean doubleEquals(double first, double second) {
		return Math.abs(first - second) < DOUBLE_COMPARISON_PRECISION;
	}

	/**
	 * Compares two doubles safely by first ensuring doubleEquals is not true.
	 * No assumptions or direct dependencies.
	 * 
	 * @param first
	 * @param second
	 * @return first < second, compared safely
	 */
	public static boolean doubleLessThan(double first, double second) {
		return !doubleEquals(first, second) && first < second;
	}

	/**
	 * Compares two doubles safely by first ensuring doubleEquals is not true.
	 * No assumptions or direct dependencies.
	 * 
	 * @param first
	 * @param second
	 * @return first <= second, compared safely
	 */
	public static boolean doubleLessThanEquals(double first, double second) {
		return doubleEquals(first, second) || first < second;
	}

	/**
	 * Compares two doubles safely by first ensuring doubleEquals is not true.
	 * No assumptions or direct dependencies.
	 * 
	 * @param first
	 * @param second
	 * @return first > second, compared safely
	 */
	public static boolean doubleGreaterThan(double first, double second) {
		return !doubleEquals(first, second) && first > second;
	}

	/**
	 * Compares two doubles safely by first ensuring doubleEquals is not true.
	 * No assumptions or direct dependencies.
	 * 
	 * @param first
	 * @param second
	 * @return first >= second, compared safely
	 */
	public static boolean doubleGreaterThanEquals(double first, double second) {
		return doubleEquals(first, second) || first > second;
	}

	/**
	 * Determines if candidateDouble represents an integer value. No
	 * assumptions, Math is dependency.
	 * 
	 * @param candidateDouble
	 * @return true if candidateDouble represents an integer
	 */
	public static boolean hasIntegerValue(double candidateDouble) {
		return MathUtil.doubleEquals(candidateDouble, Math.round(candidateDouble));
	}

	/**
	 * Returns the distance represented by a vector with components x and y. No
	 * assumptions, Math is dependency.
	 * 
	 * @param x
	 * @param y
	 * @return the distance represented
	 */
	public static double distance(double x, double y) {
		return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
	}

	/**
	 * Converts relativeXAngle, which is measured with respect to the positive x
	 * axis counterclockwise, to relativeYAngle, which is measured with respect
	 * to the positive y axis clockwise. No assumptions, Point2D is dependency.
	 * 
	 * @param relativeXAngle
	 * @return the relative angle
	 */
	public static double angleRelativeXToY(double relativeXAngle) {
		Point2D relativeXVector = polarToRectangular(new PointPolar(1.0, relativeXAngle));
		double relativeYAngle = relativeXVector.angle(UNIT_Y_VECTOR);
		if (relativeXVector.getX() < 0.0) {
			relativeYAngle *= -1;
		}
		return relativeYAngle;
	}

	/**
	 * Checks to ensure "index" is valid for a 0-indexed List (or similar data
	 * structure) of length "size." If not, throws an InvalidIndexException. No
	 * assumptions; MathUtil and InvalidIndexException are dependencies.
	 * 
	 * @param index
	 * @param size
	 * @throws InvalidIndexException
	 *             if index < 0, index >= size, or index does not represent an
	 *             integer
	 */
	public static void checkValidIndex(double index, int size) {
		if (!MathUtil.hasIntegerValue(index)) {
			throw new InvalidIndexException(RESOURCE_DOUBLE_NAME);
		}
		if (index < 0.0 || index >= size) {
			throw new InvalidIndexException(RESOURCE_BOUNDS_NAME);
		}
	}
}
