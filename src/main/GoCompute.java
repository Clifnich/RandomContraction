package main;

public class GoCompute {

	public static void main(String[] args) {
		Graph g = Graph.createGraphFromFile("kargerMinCut.txt");
		int min = g.getMinCut();
		for (int i = 0; i < Math.pow(g.getVertices().size(), 2); i++) {
			int result = g.getMinCut();
			if (result < min) min = result;
		}
		
		System.out.println("The min cut is: " + min);
	}
}
