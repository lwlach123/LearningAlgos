import java.util.Scanner;

public class KayakProblem {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int[] competitors = new int[sc.nextInt()];
		int damagedKayaks = sc.nextInt();
		int reserveKayaks = sc.nextInt();

		// Mark where all of the damaged kayaks are
		for (int i = 0; i < damagedKayaks; i++) {
			int index = sc.nextInt();
			competitors[index - 1] = competitors[index - 1] - 1;
		}

		// Mark where all of the reserve kayaks are
		for (int i = 0; i < reserveKayaks; i++) {
			int index = sc.nextInt();
			competitors[index - 1] = competitors[index - 1] + 1;
		}

		// for every element in our race
		for (int i = 0; i < competitors.length; i++) {
			// if it doesn't have a reserve, move on
			if (competitors[i] != 1)
				continue;

			// As long as there is something to our racers left
			if (i != 0) {
				// if the competitor to our left needs a reserve, give it to them
				if (competitors[i - 1] == -1) {
					competitors[i - 1] = competitors[i - 1] + 1;
					competitors[i] = competitors[i] - 1;
				}
			}

			// check to make sure we still have a reserve
			if (competitors[i] != 1)
				continue;

			// As long as there is something to our racers right
			if (i != competitors.length - 1) {
				// if the racer to our right needs a kayak
				if (competitors[i + 1] == -1) {
					competitors[i + 1] = competitors[i+ 1] + 1;
					competitors[i] = competitors[i] - 1;
				}
			}
		}

		// Now count all the competitors who still don't have a kayak
		int count = 0;
		for (int i = 0; i < competitors.length; i++) {
			if (competitors[i] == -1)
				count++;
		}
		System.out.println(count);
	}
}
