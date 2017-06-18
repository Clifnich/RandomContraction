package sequencetest;

import main.*;

public class TestWithTwoTXT {

	public static void main(String[] args) {
		Graph g = Graph.createGraphFromFile("two.txt");
		// 1. get the edge
		// 2. merge the two vertices
		//UndirectedEdge e = new UndirectedEdge(new Vertex("8"), new Vertex("7"));
		g.merge("8", "7");
		g.merge("2", "1");
		g.merge("8-7", "6");
		g.merge("8-7-6", "5");
		g.merge("8-7-6-5", "4");
		
		System.out.println("anything");
	}
}
