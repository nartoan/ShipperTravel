import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TSPModel {
	int[] X;
	boolean[] marked;
	double f;
	double best_f;
	int[] best_X;
	double[][] c;
	int length;
	private Scanner scanner;
	
	public void tryValue(int k, boolean[] marked) {
		for(int i = 0; i < length; i++) {
 			if (check(marked)) {
 				updateBest();
			} else if (marked[i] == false && i != k) {
				marked[i] = true;
				X[k] = i;
				
				tryValue(i,marked);
				
				X[k] = -1;
				marked[i] = false;
			}
		}
	}
	public boolean check(boolean[] marked) {
		for(boolean marke : marked) {
			if (!marke) {
				return false;
			}
		}
		
		return true;
	}
	
	public void updateBest() {
		for(int temp : X) {
			System.out.print(temp + " ");
		}
		System.out.println();
		 f = calcDistance(X);
		 if (best_f > f) {
			 best_X = X;
			 best_f = f;
		 }
	}
	private double calcDistance(int[] X) {
		double distance = c[0][X[0]];
		int previous = 0;
		for(int temp : X) {
			if (temp != -1) {
				distance += c[previous][temp];
				previous = temp;
			}
		}
		
		return distance;
	}
	
	public void printSolution() {
		for(int temp : best_X) {
			System.out.print(temp + " ");
		}
		System.out.println();
		System.out.println("Distance : " + best_f);
	}
	
	public void setInput() throws FileNotFoundException {
		scanner = new Scanner(new File("matrix.txt"));
		length = scanner.nextInt();
		c = new double[length][length];
		for(int i = 0 ; i < length; i++) {
			for(int j  = 0; j < length; j++) {
				c[i][j] = scanner.nextDouble();
			}
		}
		
		marked = new boolean[length];
		for(int i = 0; i < length; i++) {
			marked[i] = false;
		}
		marked[0] = true;
		
		X = new int[length];
		for(int i = 0; i < length; i++) {
			X[i] = -1;
		}
		best_X = new int[length];
		
		best_f = Double.MAX_VALUE;
	
		
		tryValue(0, marked);
		printSolution();
	}
	public static void main(String[] args) throws FileNotFoundException {
		TSPModel tspModel = new TSPModel();
		tspModel.setInput();
		
		System.out.println();
	}
}
