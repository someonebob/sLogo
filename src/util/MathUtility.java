package util;

import javafx.geometry.Point2D;

public final class MathUtility {
	public static final Point2D UNIT_X_VECTOR = new Point2D(1.0, 0.0);
	public static final Point2D UNIT_Y_VECTOR = new Point2D(0.0, 1.0);
	public static final double DOUBLE_COMPARISON_PRECISION = 0.000001;
	
	public static void main(String[] args){
		PointPolar p = MathUtility.rectangularToPolar(new Point2D(-2, 1));
		System.out.println(p.getDistance());
		System.out.println(p.getAngle());
		Point2D p2 = MathUtility.polarToRectangular(p);
		System.out.println(p2.getX());
		System.out.println(p2.getY());
	}
	
	public static PointPolar rectangularToPolar(Point2D rectangularPoint) {
		return new PointPolar(rectangularPoint.magnitude(), rectangularPoint.angle(UNIT_X_VECTOR));
	}

	public static Point2D polarToRectangular(PointPolar polarPoint) {
		return new Point2D(polarPoint.getDistance() * Math.cos(Math.toRadians(polarPoint.getAngle())),
				polarPoint.getDistance() * Math.sin(Math.toRadians(polarPoint.getAngle())));
	}
	
	public static boolean doubleEquals(double first, double second){
		return Math.abs(first - second) < DOUBLE_COMPARISON_PRECISION;
	}
	
	//Adding more methods here
}
