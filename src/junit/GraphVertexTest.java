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
	public void testThatVertexScaleShouldDecreaseAfterMerge() {
		Graph g = Graph.createGraphFromFile("verySimple.txt");
//		Map<String, Vertex> vertexMap = g.getVertices();
//		Vertex v1 = vertexMap.get("1");
//		Vertex v2 = vertexMap.get("2");
		g.merge("1", "2");
		Map<String, Vertex> vertexMap = g.getVertices();
		assertEquals(1, vertexMap.size());
	}
	
	public void testThatNewVertexHasProperComponentSize() {
		Graph g = Graph.createGraphFromFile("verySimple.txt");
		g.merge("1", "2");
		Map<String, Vertex> map = g.getVertices();
		Vertex v = map.get("1-2");
		List<Vertex> components = v.getComponents();
		assertEquals(2, components.size());
	}
	
	public void testThatNewVertexHasProperComponents() {
		Graph g = Graph.createGraphFromFile("verySimple.txt");
		g.merge("1", "2");
		Map<String, Vertex> map = g.getVertices();
		Vertex v = map.get("1-2");
		
		Vertex v1 = new Vertex("1");
		Vertex v2 = new Vertex("2");
		List<Vertex> components = v.getComponents();
		assertTrue(v1.equals(components.get(0)) || v1.equals(components.get(1)));
		assertTrue(v2.equals(components.get(0)) || v2.equals(components.get(1)));
	}
	
	public void testThatCaseOneHasNoADJVertices() {
		Graph g = Graph.createGraphFromFile("verySimple.txt");
		g.merge("1", "2");
		Map<String, Vertex> map = g.getVertices();
		Vertex v = map.get("1-2");
		List<Vertex> list = v.getAdjacentList();
		assertEquals(0, list.size());
	}
	
	public void testThatCaseTwoHasOnlyOneADJVertex() {
		Graph g = Graph.createGraphFromFile("verySimple1.txt");
		g.merge("1", "3");
		Map<String, Vertex> map = g.getVertices();
		Vertex v = map.get("1-3");
		List<Vertex> list = v.getAdjacentList();
		assertEquals(1, list.size());
		assertTrue(list.get(0).equals(new Vertex("2")));
	}
	
	/**
	 * Test adjacent's adjacent,
	 * created from createGraphFromFile method
	 */
	public void testMethodAdjacent() {
		Graph g = Graph.createGraphFromFile("verySimple.txt");
		Vertex v1 = g.getVertices().get("1");
		assertTrue(v1.getAdjacentList().get(0).getAdjacentList().get(0).equals(v1));
	}
}
