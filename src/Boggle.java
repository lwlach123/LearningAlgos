import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeSet;

public class Boggle {
	public static void main(String[] args) {
		/**
		 * PART 1: Setup for the board
		 * 
		 * Reading the input from the user, sets up a 3 dimensional array keeping track
		 * of all the boggle boards
		 */
		Scanner sc = new Scanner(System.in);

		int length = Integer.parseInt(sc.nextLine());

		HashMap<Character, ArrayList<String>> dictionary = new HashMap<>();

		for (int i = 0; i < length; i++) {
			String s = sc.nextLine();
			ArrayList<String> holder = new ArrayList<>();
			if (dictionary.containsKey(s.charAt(0))) {
				dictionary.get(s.charAt(0)).add(s);
			} else {
				holder.add(s);
				dictionary.put(s.charAt(0), holder);
			}

		}

		int numBoards = sc.nextInt();

		// 3 dimensional array, An array of 2 dimensional Arrays representing our
		// different boards
		char[][][] arr = new char[numBoards][4][4];

		for (int outer = 0; outer < numBoards; outer++) {
			// Grabs the Empty Line so we don't get errors
			sc.nextLine();
			for (int row = 0; row < 4; row++) {
				String reader = sc.nextLine();
				for (int column = 0; column < 4; column++) {
					arr[outer][row][column] = reader.charAt(column);
				}
			}
		}

		Comparator<String>compare = (x,y) ->{
			return x.length() - y.length();
		};
		sc.close();
		TreeSet<String> wordsFound = new TreeSet<String>(compare);
		// for every boggle board we have
		for (int board = 0; board < arr.length; board++) {

			// for every row in that board
			for (int row = 0; row < arr[board].length; row++) {

				// for every letter in that row
				for (int letter = 0; letter < arr[board][row].length; letter++) {

					// if we have a word/s that starts with that letter
					if (dictionary.containsKey(arr[board][row][letter])) {

						// perform the backtracking algorithm to see if we can form that word on the
						// board
						for (String s : dictionary.get(letter)) {
							String result = backTracking(arr, board, row, letter, s, 0);
							if (result.equals(s)) {
								wordsFound.add(result);
							}
						}
					}
				}
			}
			int total = 0;
			String longest = wordsFound.last();
			for(String s : wordsFound) {
				int val = s.length();
				
				switch(val) {
					case 5:
						total += 2;
						break;
					case 6:
						total += 3;
						break;
					case 7:
						total += 5;
						break;
					case 8:
						total += 11;
						break;
					default:
						total += 3;
						break;
				}
			}
			
			System.out.println(String.format("%d %s %d", total,longest,wordsFound.size()));
			wordsFound.clear();

			
		}
		
		
	}

	private static String backTracking(char[][][] arr, int board, int row, int letter, String target, int tally) {
		// if out of bounds vertically, horizontally, or we have checked too many
		// letters, return the empty String
		if (row >= 4 || letter >= 4 || tally >= target.length()) {
			return "";
		}

		ArrayList<String> results = new ArrayList<>();

		results.add(backTracking(arr, board, row + 1, letter, target, tally + 1));
		results.add(backTracking(arr, board, row + 1, letter + 1, target, tally + 1));
		results.add(backTracking(arr, board, row, letter + 1, target, tally + 1));
		results.add(backTracking(arr, board, row - 1, letter + 1, target, tally + 1));
		results.add(backTracking(arr, board, row - 1, letter, target, tally + 1));
		results.add(backTracking(arr, board, row - 1, letter - 1, target, tally + 1));
		results.add(backTracking(arr, board, row, letter - 1, target, tally + 1));
		results.add(backTracking(arr, board, row + 1, letter - 1, target, tally + 1));

		String result = "";
		String template = target.substring(target.length() - tally, target.length());
		for (String s : results) {
			if (!s.isEmpty()) {
				String temp = arr[board][row][letter] + s;
				// if our result, coupled with
				if (temp.equals(template)) {
					result = temp;
				}
			}
		}

		return result;

	}
}
