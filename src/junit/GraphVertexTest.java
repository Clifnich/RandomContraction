package junit;

import junit.framework.TestCase;
import main.*;
import java.util.*;

public class GraphVertexTest extends TestCase {

	public GraphVertexTest(String name) {
		super(name);
	}
	
	/**
	 * Test that the createGraphFromFile method
	 * gets the vertices ready excluding the adjacentList
	 */
	public void testThatCreateGraphFromFileGetsVerticesReady() {
		Graph g = Graph.createGraphFromFile("verySimple.txt");
		Map<String, Vertex> map = g.getVertices();
		Vertex v1 = map.get("1");
		Vertex v2 = map.get("2");
		assertTrue(v1 != null);
		assertTrue(v2 != null);
	}
	
	/**
	 * Test that the createGraphFromFile method
	 * gets the vertices ready including the adjacentList
	 */
	public void testThatCreateGraphFromFileGetsAdjacentListReady() {
		Graph g = Graph.createGraphFromFile("verySimple.txt");
		Map<String, Vertex> map = g.getVertices();
		Vertex v1 = map.get("1");
		Vertex v2 = map.get("2");
		List<Vertex> list = v1.getAdjacentList();
		assertTrue(list.get(0).equals(new Vertex("2")));
		
		list = v2.getAdjacentList();
		assertTrue(list.get(0).equals(new Vertex("1")));
	}
	
	/**
	 * Test that the createGraphFromFile method
	 * gets the vertices ready including the adjacentList
	 * Another source of data, testing that it works when
	 * the adjacent list size is more than 1
	 */
	public void testThatCreateGraphFromFileGetsAdjacentListReady1() {
		Graph g = Graph.createGraphFromFile("verySimple1.txt");
		Map<String, Vertex> map = g.getVertices();
		Vertex v1 = map.get("1");
		List<Vertex> list = v1.getAdjacentList();
		assertTrue(list.get(0).equals(new Vertex("2")));
		assertTrue(list.get(1).equals(new Vertex("3")));
	}
}
