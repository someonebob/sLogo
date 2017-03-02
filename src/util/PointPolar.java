package util;


/**
 * This class stores data representing a point in polar coordinates.
 * It has a distance and angle field for this purpose, where angle is
 * in degrees.
 * @author Matthew Barbano
 *
 */
public class PointPolar {
	private double distance;
	private double angle;

	public PointPolar(double distance, double angle) {
		this.distance = distance;
		this.angle = angle;
	}

	public double getDistance() {
		return distance;
	}

	public double getAngle() {
		return angle;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}
}
