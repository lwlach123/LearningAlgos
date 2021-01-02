
// 4 Thought on Katlin
public class KatlinPractice1 {

	/**
	 * Given an Integer n, use 4 4's to make that value
	 * 
	 * Disgusting brute force, but we will give it a shot
	 */

	static String q1(int n) {
		String t = "NO SOLUTION";

		String[] arr = { "+", "-", "/", "*" };
		StringBuffer holder = new StringBuffer();

		for (int one = 0; one <= 3; one++) {
			for (int two = 0; two <= 3; two++) {
				for (int three = 0; three <= 3; three++) {
					holder.append("4" + arr[one] + "4" + arr[two] + "4" + arr[three] + "4");
					if (eval(holder.toString()) == n)
						return holder.toString();
					holder.delete(0, holder.length());
				}
			}
		}

		return "NO SOLUTION";
	}

	static private int eval(String equation) {
		int val = 4;
		
		for(int i = 0; i <=2; i ++) {
			char c = equation.charAt(1 + (2*i));
			if(c == '+')
				val += 4;
			else if(c == '-')
				val -=4;
			else if(c == '*')
				val *= 4;
			else
				val /= 4;
			
	}
		return val;
	}

}
