package calculation;

public class SqrPolinom {
	private final int K = 3, pts = 5; // Ê-polinom degree,
	// pts-the number of points
	private final int N = 5;// arrays length
	private double x[], y[], b[], sums[][];
	private double a[];

	public SqrPolinom() {
		a = new double[N];
		b = new double[N];
		x = new double[N];
		y = new double[N];
		sums = new double[N][N];
	}

	public SqrPolinom(double x[], double y[]) {
		refreshMass();
		setXYmasMeanings(x, y);
	}

	public void setXYmasMeanings(double x[], double y[]) {
		this.x = x;
		this.y = y;
	}

	public void culculate() {
		refreshMass();
		feelingTheKoefOfTheSystem();
		feelingTheKoefColumnOfFreeElem();
		gausMethod();
		calcKoefAproxPolinom();
	}

	public double Power(double v, int p) {
		if (p == 0)
			return 1;
		if (p > 1)
			v *= Power(v, p - 1);
		return v;
	}

	private void feelingTheKoefOfTheSystem() {
		for (int i = 0; i < K + 1; i++) {
			for (int j = 0; j < K + 1; j++) {
				sums[i][j] = 0;
				for (int k = 0; k < pts; k++) {
					sums[i][j] += Power(x[k], i + j);
				}
			}
		}
	}

	private void showTheKoefOfTheSystem() {
		for (int i = 0; i < K + 1; i++) {
			for (int j = 0; j < K + 1; j++) {
				System.out.print(sums[i][j] + "  ");
			}
			System.out.println();
		}
	}

	private void feelingTheKoefColumnOfFreeElem() {
		// feel in the column of free members 
		for (int i = 0; i < K + 1; i++) {
			b[i] = 0;
			for (int k = 0; k < pts; k++)
				b[i] += Power(x[k], i) * y[k];
		}
		
	}

	private void gausMethod() {
		// apply the Gauss method to reduce the matrix of the system to a triangular form
		double M;
		for (int k = 0; k < K + 1; k++) {
			for (int i = k + 1; i < K + 1; i++) {
				M = sums[i][k] / sums[k][k];
				for (int j = k; j < K + 1; j++)
					sums[i][j] -= M * sums[k][j];
				b[i] -= M * b[k];
			}
		}
	}

	private void calcKoefAproxPolinom() {
		// calculate the coefficient approximation polynom
		double s;
		for (int i = K; i >= 0; i--) {
			s = 0;
			for (int j = i; j < K + 1; j++)
				s += sums[i][j] * a[j];
			a[i] = (b[i] - s) / sums[i][i];
		}
	}

	private void refreshMass() {
		a = new double[N];
		b = new double[N];
		sums = new double[N][N];
	}

	public double getMeaning(double x) {
		return a[0] + a[1] * x + a[2] * x * x + a[3] * x * x * x;
	}
}
