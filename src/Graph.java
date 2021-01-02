import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;

// Directed Graph, 2d Array Approach
public class Graph {
	int [][] coordinates;
	
	public Graph() {
		coordinates = new int [11][11];
	}
	
	/**
	 * By default, all spots will be zero, indicating no connection
	 * When a spot is added, it will turn to 1
	 * @param startNode
	 * @param endNode
	 */
	Graph addConnection(int startNode, int endNode) {
		coordinates[startNode][endNode] = 1;
		coordinates[endNode][startNode] = 1;
		return this;
	}
	
	boolean hasConnection(int startNode, int endNode){
		return coordinates[startNode][endNode] == 1;
	}
	
	ArrayList<Integer> breadthFirstSearch(int startNode){
		ArrayList<Integer>visited = new ArrayList<>();
		ArrayDeque<Integer>discovered = new ArrayDeque<>();
		
		discovered.add(startNode);
		
		while(!discovered.isEmpty()) {
		int currVal = discovered.remove();
		visited.add(currVal);
		for(int i = 0; i < coordinates[startNode].length; i ++) {
			if(!visited.contains(i) && coordinates[currVal][i] == 1)
				discovered.add(i);
		}
	}
		return visited;
	}
	
	ArrayList<Integer> depthFirstSearch(int startNode){
		ArrayList<Integer>visited = new ArrayList<>();
		ArrayDeque<Integer>discovered = new ArrayDeque<>();
		
		discovered.push(startNode);
		
		while(!discovered.isEmpty()) {
		int currVal = discovered.pop();
		visited.add(currVal);
		for(int i = 0; i < coordinates[startNode].length; i ++) {
			if(!visited.contains(i) && coordinates[currVal][i] == 1)
				discovered.push(i);
		}
	}
		return visited;
	}
	
		
	
}
