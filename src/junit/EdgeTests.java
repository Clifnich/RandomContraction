package junit;

import junit.framework.TestCase;
import main.*;

public class EdgeTests extends TestCase {

	public EdgeTests(String name) {
		super(name);
	}
	
	/**
	 * Test length element in the Edge class
	 */
	public void testUndirectedEdgeInit() {
		Vertex v1 = new Vertex();
		Vertex v2 = new Vertex();
		Edge e = new UndirectedEdge(v1, v2);
		assertTrue(e != null);
		
		e.setLength(5);
		assertEquals(e.getLength(), 5);
	}
	
	/**
	 * Test the getters
	 */
	public void testGetter() {
		Vertex v1 = new Vertex();
		Vertex v2 = new Vertex();
		UndirectedEdge e = new UndirectedEdge(v1, v2);
		assertTrue(e.getVertex1().equals(v1));
		assertTrue(e.getVertex2().equals(v2));
	}
	
	/**
	 * Test resembles method.
	 * As long as two edges have the same vertices,
	 * they are considered as "resembled"
	 */
	public void testResemble() {
		Vertex v1 = new Vertex();
		Vertex v2 = new Vertex();
		UndirectedEdge e1 = new UndirectedEdge(v1, v2);
		UndirectedEdge e2 = new UndirectedEdge(v2, v1);
		assertTrue(e1.resembles(e2));
	}
	
	/**
	 * Test if an undirected edge can successfully
	 * move on
	 */
	public void testMoveOn() {
		Vertex v1 = new Vertex("1");
		Vertex v2 = new Vertex("2");
		Vertex v3 = new Vertex("3");
		UndirectedEdge e = new UndirectedEdge(v1, v2);
		e.moveOn(v2, v3);
		assertTrue(v3.equals(e.getVertex1()) || v3.equals(e.getVertex2()));
	}
}
