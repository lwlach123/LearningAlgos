
public class Driver {
	public static void main(String [] args) {
		Graph g = new Graph();
		g.addConnection(1, 4).addConnection(4, 6).addConnection(4, 10).addConnection(4, 8);
		g.addConnection(8, 3).addConnection(3, 2).addConnection(2, 5);
		
		System.out.println(g.breadthFirstSearch(1));
		System.out.println(g.depthFirstSearch(1));
		
	}
}
