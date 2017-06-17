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
}
