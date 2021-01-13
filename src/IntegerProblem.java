import java.util.ArrayList;
import java.util.Scanner;

public class IntegerProblem {
	public static void main(String [] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		int c = sc.nextInt();
		
		ArrayList<Double>ints = new ArrayList<>();
		for(int i = 0; i <= 81; i ++) {
			ints.add(a * Math.pow(i, b) + c);
		}
		
		System.out.println(ints);
	}
}
