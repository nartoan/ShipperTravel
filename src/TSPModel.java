public class TSPModel {
	int[] X;
	boolean[] marked;
	double f;
	double best_f;
	int[] best_X;
	double[][] c;
	int length;

	public void tryValue(int k) {
		if (checkSolution()) {
			updateBest();
		} else {
			for (int i = 1; i < length; i++) {
				if (marked[i] == false) {
					marked[i] = true;
					X[k + 1] = i;

					tryValue(k + 1);

					X[k + 1] = -1;
					marked[i] = false;
				}
			}
		}
	}

	public boolean checkSolution() {
		for (boolean marke : marked) {
			if (!marke) {
				return false;
			}
		}

		return true;
	}

	private void updateBest() {
		f = calcDistance();

		for (int temp : X) {
			System.out.print(temp + " ");
		}
		System.out.println("Distance : " + f);

		if (best_f > f) {
			System.arraycopy(X, 0, best_X, 0, length);
			best_f = f;
		}
	}

	private double calcDistance() {
		double distance = c[0][X[0]];
		int previous = 0;
		for (int temp : X) {
			if (temp != -1) {
				distance += c[previous][temp];
				previous = temp;
			}
		}
		distance += c[previous][0];

		return distance;
	}

	public void printSolution() {
		System.out.print("Travel : ");
		for (int temp : best_X) {
			System.out.print(temp + " ");
		}
		System.out.println();
		System.out.println("Distance : " + best_f);
	}

	public String solution() {
		String solution = "Travel : \n";
		for (int temp : best_X) {
			solution += temp + " ";
		}
		solution += "\nDistance : " + best_f;

		return solution;
	}

	public void setInput(double[][] cInput) {
		length = cInput[0].length;
		c = new double[length][length];
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length; j++) {
				c[i][j] = cInput[i][j];
			}
		}

		marked = new boolean[length];
		for (int i = 0; i < length; i++) {
			marked[i] = false;
		}
		marked[0] = true;

		X = new int[length];
		for (int i = 0; i < length; i++) {
			X[i] = -1;
		}
		best_X = new int[length];

		best_f = Double.MAX_VALUE;

		X[0] = 0;
	}
}
