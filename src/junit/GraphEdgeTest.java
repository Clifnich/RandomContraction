package junit;

import junit.framework.TestCase;
import main.*;
import java.util.*;

public class GraphEdgeTest extends TestCase {

	public GraphEdgeTest(String name) {
		super(name);
	}
	
	/**
	 * Test that getUndirectedEdge method doesn't return null
	 */
	public void testGetEdgeNotNull() {
		Graph g = Graph.createGraphFromFile("verySimple.txt");
		assertTrue(g.getUndirectedEdge() != null);
	}
	
	/**
	 * For verySimple.txt
	 * the graph should only have one edge
	 */
	public void testOneEdge() {
		Graph g = Graph.createGraphFromFile("verySimple.txt");
		Vertex v1 = new Vertex("1");
		Vertex v2 = new Vertex("2");
		UndirectedEdge e = new UndirectedEdge(v1, v2);
		List<UndirectedEdge> list = g.getUndirectedEdge();
		assertEquals(list.size(), 1);
		assertTrue(list.get(0).resembles(e));
	}
	
	/**
	 * In this test case we have
	 * two edges at total
	 */
	public void testTwoEdge() {
		Graph g = Graph.createGraphFromFile("verySimple1.txt");
		Vertex v1 = new Vertex("1");
		Vertex v2 = new Vertex("2");
		Vertex v3 = new Vertex("3");
		UndirectedEdge e1 = new UndirectedEdge(v1, v2);
		UndirectedEdge e2 = new UndirectedEdge(v1, v3);
		List<UndirectedEdge> list = g.getUndirectedEdge();
		assertEquals(list.size(), 2);
		assertTrue(list.get(0).resembles(e1));
		assertTrue(list.get(1).resembles(e2));
	}
	
	public void testCreateRandomEdge() {
		Graph g = Graph.createGraphFromFile("verySimple.txt");
		UndirectedEdge e_random = g.getRandomUEdge();
		Vertex v1 = new Vertex("1");
		Vertex v2 = new Vertex("2");
		UndirectedEdge e0 = new UndirectedEdge(v1, v2);
		assertTrue(e0.resembles(e_random));
	}
	
	public void testCreateRandomEdge1() {
		Graph g = Graph.createGraphFromFile("verySimple1.txt");
		UndirectedEdge e_random = g.getRandomUEdge();
		Vertex v1 = new Vertex("1");
		Vertex v2 = new Vertex("2");
		Vertex v3 = new Vertex("3");
		UndirectedEdge e1 = new UndirectedEdge(v1, v2);
		UndirectedEdge e2 = new UndirectedEdge(v1, v3);
		assertTrue(e_random.resembles(e1) || e_random.resembles(e2));
	}
	
	/**
	 * Test that after merging two vertices,
	 * the number of edges will decrease by one
	 */
	public void testEdgeNumberDecreasesAfterMerge() {
		Graph g = Graph.createGraphFromFile("verySimple.txt");
		g.merge("1", "2");
		assertEquals(0, g.getUndirectedEdge().size());
	}
	
	/**
	 * Increase the input size,
	 * now the number of edges is supposed to be 1 after merging
	 */
	public void testEdgeNumberDecreasesAfterMerge1() {
		Graph g = Graph.createGraphFromFile("verySimple1.txt");
		g.merge("1", "3");
		assertEquals(1, g.getUndirectedEdge().size());
	}
}
