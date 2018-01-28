import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TSPSolver {
	private Scanner scanner;
	TSPModel model;
	
	public TSPSolver() {
		model = new TSPModel();
	}
	
	public String solver() {
		model.tryValue(0);
		model.printSolution();
		return model.solution();
	}
	
	public void readInnput(String filename) throws FileNotFoundException {
		scanner = new Scanner(new File(filename));
		int length = scanner.nextInt();
		double[][] c = new double[length][length];
		for(int i = 0 ; i < length; i++) {
			for(int j  = 0; j < length; j++) {
				c[i][j] = scanner.nextDouble();
			}
		}
		
		model.setInput(c);
	}
}
