package calculation;

public class Lagranj {
	private double sum = 0;
	private double mul;

	public double lagranjCalculating(double x, double[] xv, double[] yv) {
		sum = 0;
		mul = 1;
		for (int i = 0; i < xv.length; i++) {
			mul = 1;
			for (int j = 0; j < xv.length; j++) {
				if (i != j) {

					mul *= (x - xv[j]) / (xv[i] - xv[j]);
				}
			}
			sum += yv[i] * mul;
		}
		return sum;
	}
}
