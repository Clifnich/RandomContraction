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
	
	/**
	 * Will comments help?
	 */
	public void vertexScaleShouldDecreaseAfterMerge() {
		Graph g = Graph.createGraphFromFile("verySimple.txt");
//		Map<String, Vertex> vertexMap = g.getVertices();
//		Vertex v1 = vertexMap.get("1");
//		Vertex v2 = vertexMap.get("2");
		fail();
		g.merge("1", "2");
		Map<String, Vertex> vertexMap = g.getVertices();
		assertEquals(1, vertexMap.size());
	}
}
