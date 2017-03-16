package util;

/**
 * This class stores data representing a point in polar coordinates. It has a
 * distance and angle field for this purpose, where angle is assumed to be in
 * degrees. There are no restrictions on the sign of distance nor on the
 * acceptable values of angle. The only dependencies are on primitives (double).
 * Example of use from the MathUtil class:
 * 
 * <pre>
 * {@code
 * 	public static Point2D polarToRectangular(PointPolar polarPoint)
	{
		return new Point2D(polarPoint.getDistance() * Math.cos(Math.toRadians(polarPoint.getAngle())),
				polarPoint.getDistance() * Math.sin(Math.toRadians(polarPoint.getAngle())));
	}
 * </pre>
 * 
 * @author Matthew Barbano
 *
 */
public class PointPolar {
	private double distance;
	private double angle;

	/**
	 * Instantiates a new PointPolar object with "distance" and "angle"
	 * parameters. Angle assumed to be in degrees, only primitive dependency is
	 * on double.
	 * 
	 * @param distance
	 * @param angle
	 */
	public PointPolar(double distance, double angle) {
		this.distance = distance;
		this.angle = angle;
	}

	/**
	 * Returns the distance field. Angle assumed to be in degrees, only
	 * primitive dependency is on double.
	 * 
	 * @return distance
	 */
	public double getDistance() {
		return distance;
	}

	/**
	 * Returns the angle field. Angle assumed to be in degrees, only primitive
	 * dependency is on double.
	 * 
	 * @return angle
	 */
	public double getAngle() {
		return angle;
	}

	/**
	 * Sets the distance field to "distance". Angle assumed to be in degrees,
	 * only primitive dependency is on double.
	 * 
	 * @param distance
	 */
	public void setDistance(double distance) {
		this.distance = distance;
	}

	/**
	 * Sets the angle field to "angle". Angle assumed to be in degrees, only
	 * primitive dependency is on double.
	 * 
	 * @param angle
	 */
	public void setAngle(double angle) {
		this.angle = angle;
	}
}
