package Calculation;

public class ArraysCalculation {
	private Lagranj lj = new Lagranj();
	private SqrPolinom sp = new SqrPolinom();
	private double x[] = new double[5];
	private double y[] = new double[5];
	private double xgragh[];
	private double yLagranj[];
	private double ySqrPolinom[];

	public ArraysCalculation(double firstBorder, double secondBorder, int numberOfMeannings) {
		xgragh = new double[numberOfMeannings];
		genXAllMeanings(firstBorder, secondBorder, numberOfMeannings);
		yLagranj = new double[numberOfMeannings];
		ySqrPolinom = new double[numberOfMeannings];
		
	}

	public void setXYmeanings(double x[], double y[]) {
		this.x = x;
		this.y = y;

	}

	private void genXAllMeanings(double firstBorder, double secondBorder, int numberOfMeannings) {
		double step = (secondBorder - firstBorder) / (numberOfMeannings - 1);
		xgragh = new double[numberOfMeannings];
		for (int i = 0; i < xgragh.length; i++) {
			xgragh[i] = firstBorder + step * i;
		}

	}

	public void genYMassivesOfMeanings() {
		sp.setXYmasMeanings(x, y);
		sp.culculate();
		for (int i = 0; i < xgragh.length; i++) {
			yLagranj[i] = lj.lagranjCalculating(xgragh[i], x, y);
			ySqrPolinom[i] = sp.getMeaning(xgragh[i]);
		}
	}

	public double[] getxgraph() {
		return xgragh;
	}

	public double[] getyLagranj() {
		return yLagranj;
	}

	public double[] getySqrPolinom() {
		return ySqrPolinom;
	}
}
