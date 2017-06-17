package junit;

import junit.framework.TestCase;
import main.*;

public class TestGraph extends TestCase {

	public TestGraph(String name) {
		super(name);
	}
	
	public void testInstantiate() {
		Graph g = new Graph();
		assertTrue(g != null);
	}
	
	public void testInitialVertices() {
		Graph g = new Graph();
		assertEquals(g.getVertices().size(), 0);
	}
	
	/**
	 * The graph should add vertices properly
	 */
	public void testAddVertex() {
		Graph g = new Graph();
		Vertex v = new Vertex();
		g.add(v);
		assertEquals(g.getVertices().size(), 1);
	}
	
	/**
	 * Test createGraphFromFile
	 */
	public void testCreate() {
		Graph g = Graph.createGraphFromFile("verySimple.txt");
		assertTrue(g != null);
	}
	
	/**
	 * Test min-cut calculation
	 */
	public void testSimpleMinCut() {
		Graph g = Graph.createGraphFromFile("verySimple.txt");
		assertEquals(1, g.getMinCut());
	}
	
	public void testSimple1MinCut() {
		Graph g = Graph.createGraphFromFile("verySimple1.txt");
		assertEquals(1, g.getMinCut());
	}
	
	public void testMinCut0() {
		Graph g = Graph.createGraphFromFile("two.txt");
		//assertEquals(2, g.getMinCut());
	}
}
