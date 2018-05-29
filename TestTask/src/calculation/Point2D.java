package calculation;

import java.io.Serializable;

public class Point2D implements Serializable {
	
	public double x, y;

	public Point2D() {
		x = 0;
		y = 0;
	}

	public Point2D(double X, double Y) {
		x = X;
		y = Y;
	}

	public void sortByY(Point2D[] points) {
		Point2D temp;
		for (int i = 0; i < points.length; i++) {
			for (int j = 0; j < points.length - 1; j++) {

				if (points[j].y < points[j + 1].y) {
					temp = points[j];
					points[j] = points[j + 1];
					points[j + 1] = temp;
				}
			}
		}
	}

	public double calcModuleOfVector() {
		return Math.sqrt((x * x) + (y * y));
	}

	public Point2D normalizeVectorR() {
		double module = calcModuleOfVector();
		return new Point2D(x / module, y / module);
	}

	public void normalizeVector() {
		double module = calcModuleOfVector();
		x /= module;
		y /= module;

	}
	
}
