package util;

public class PointPolar {
	double distance;
	double angle;

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
