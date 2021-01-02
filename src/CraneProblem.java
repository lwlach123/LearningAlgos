import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;


public class CraneProblem {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);

		int length = sc.nextInt();
		int width = sc.nextInt();
		int craneLoc = sc.nextInt();
		int reach = sc.nextInt();

		HashMap<int [], HashMap<Integer, ArrayList<int []>>>cranes = new HashMap<>();
		
		int [] wall1 = {-length/2, 0};
		int [] wall2 = {length/2,0};
		int [] wall3 = {0,-width/2};
		int [] wall4 = {0,width/2};
		
		HashSet<int[]>set = new HashSet<>();
		set.add(wall1);
		set.add(wall2);
		set.add(wall3);
		set.add(wall4);
		
		while(sc.hasNextInt()) {
			int [] temp = {sc.nextInt(), sc.nextInt()};
			ArrayList<int []>addition = new ArrayList<>();
			for(int [] coords : set) {
				if(
						
Math.sqrt(Math.pow(coords[0]-temp[0], 2) + Math.pow(coords[1]-temp[1],2)) <= reach
						
						) {
					addition.add(coords);
				}
			}
			HashMap<Integer,ArrayList<int[]>>insertion = new HashMap<>();
			insertion.put(addition.size(), addition);
			cranes.put(temp, insertion);
		}
		
		ArrayList<int []>holder = new ArrayList<>(cranes.keySet());
		int total = 0;
		
		while(!set.isEmpty() && !holder.isEmpty()) {
			
			int [] arr = {};
			int l = -1;
		
			/**
			 * Our crane with the most walls atm
			 * 
			 * Key in big hashmap represents the crane location
			 * Key in small hashmap represents wall score
			 * Value in small hashmap represents walls covered 
			 */
			for(int [] q : holder) {
				int val = 0;
				for(Integer i : cranes.get(q).keySet())
					val += i;
				if (val > l) {
					l = val;
					arr = q;
				}
					
					
			}
			
			/**
			 * For every wall that was taken by the largest amount,
			 * deduct it from the set
			 */
			ArrayList<int []> other = cranes.get(arr).get(l);
			for(int [] u : other) {
				if(set.contains(u))
					set.remove(u);
			}
			holder.remove(arr);
			cranes.remove(arr);
			
			/**
			 * Now, readust the scores for all of the remaining walls
			 */
			
			for(int [] u : cranes.keySet()) {
				byte val = 0;
				for(Integer i : cranes.get(u).keySet())
					val += i;
				ArrayList<int []>values = cranes.get(u).get((int)val);
				byte counter = 0;
				for(int [] i : values) {
					if(set.contains(i)) {
						counter ++;
					}
				}
				HashMap<Integer, ArrayList<int []>>m = new HashMap<>();
				m.put((int)counter,cranes.get(u).get((int)val));
				cranes.replace(u, null);
				cranes.replace(u, m);
			}
			total ++;
			
			
			
		
		
	}
		
		if(!set.isEmpty())
			System.out.println("impossible");
		else
			System.out.println(total);
}
}
