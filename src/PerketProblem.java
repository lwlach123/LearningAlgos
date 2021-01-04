import java.util.HashMap;
import java.util.Scanner;

public class PerketProblem {
	public static void main(String [] args) {
		Scanner sc = new Scanner(System.in);
		
		HashMap<Integer, int []>map = new HashMap<>();
		
		int tally = sc.nextInt();
		for(int i = 0; i < tally; i ++) {
			int [] ingredientValues = {sc.nextInt(), sc.nextInt()};
			map.put(i, ingredientValues);
		}
		
		HashMap<Long, Integer>previousValues = new HashMap<>();
		int lowestValue = Integer.MAX_VALUE;
		String combo = "";
		
		int size = map.keySet().size();
		
		HashMap<String, int []>results = lowestCombo(map,0,map.keySet().size());
		
		String result = "";
		for(String s : results.keySet())
			result += s;
		System.out.println(result.length());
		
//		if(size > 0) {
//			for(Integer a : map.keySet()) {
//				if(size > 1) {
//					for(Integer b : map.keySet()) {
//						if(size > 2) {
//							for(Integer c : map.keySet()) {
//								if(size > 3) {
//									for(Integer d : map.keySet()) {
//										if(size > 4) {
//											for(Integer e : map.keySet()) {
//												if(size > 5) {
//													for(Integer f : map.keySet()) {
//														if(size > 6) {
//															for(Integer g : map.keySet()) {
//																if(size > 7) {
//																	for(Integer h : map.keySet()) {
//																		if(size > 8) {
//																			for(Integer i : map.keySet()) {
//																				if(size > 9) {
//																					for(Integer j : map.keySet()) {
//																						int sourness = map.get(a)[0] * map.get(b)[0]* map.get(c)[0]* map.get(d)[0]* map.get(e)[0]* map.get(f)[0] *  map.get(g)[0]* map.get(h)[0]* map.get(i)[0]* map.get(j)[0];
//																						int bitterness = map.get(a)[1] + map.get(b)[1]+ map.get(c)[1]+ map.get(d)[1]+ map.get(e)[1]+ map.get(f)[1] +  map.get(g)[1]+ map.get(h)[1]+ map.get(i)[1]+ map.get(j)[1];
//																						if(Math.abs(sourness-bitterness) < lowestValue) {
//																							lowestValue = Math.abs(sourness-bitterness);
//																							combo = String.format("%d%d%d%d%d%d%d%d%d%d", a,b,c,d,e,f,g,h,i,j);
//																						}
//																					}
//																				}
//																			}
//																		}
//																	}
//																}
//															}
//														}
//													}
//												}
//											}
//										}
//									}
//								}
//							}
//						}
//					}
//				}
//			}
//		}
	}
	
	static HashMap<String, int []> lowestCombo(HashMap<Integer,int []> map, int tally, int maximum){
		// return a brand new hashmap when we reach the end of our ingredients
		if(tally == maximum)
			return new HashMap<String,int []>();
		
			/**
			 * Awaits the results of the past, will take the results
			 * of the greatest of all iterations prior. 
			 */
		HashMap<String,int []>greatest = lowestCombo(map,tally+1, maximum);
		
		// gets the greatest combo of the previous iterations
		int [] pairing = new int[2];
		for(int [] i : greatest.values()) {
			pairing = i;
		}
		
		int [] temp = new int[2];
		int total;
		if(pairing == null)
			total = Integer.MAX_VALUE;
		else
			total = Math.abs(pairing[0] - pairing[1]);
		String s = "";
		// checks to see if any new combonations beat the previous one
		for(Integer i : map.keySet()) {
			// if there hasn't been a winner before
			if(pairing == null) {
				int amount = Math.abs(map.get(i)[0] - map.get(i)[1]);
				if(amount < total) {
					temp = map.get(i);
					total = amount;
					s = String.valueOf(i);
				}
			}
			/**
			 * If there has been a winner before, compare the values
			 */
			int [] holder = map.get(i);
			int sourness = pairing[0] * holder[0];
			int bitterness = pairing[1] + holder[1];
			
			// if its less than our current leaders total, assign the values
			if(Math.abs(sourness - bitterness) < total) {
				temp[0] = sourness;
				temp[1] = bitterness;
				total = Math.abs(sourness - bitterness);
				s = String.valueOf(i);
			}
		}
		String previousWinner = "";
		for(String o : greatest.keySet())
			previousWinner += o;
		greatest = new HashMap<>();
		greatest.put(previousWinner + s, temp);
		
		return greatest;
	}
}
