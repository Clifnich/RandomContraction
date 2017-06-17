package junit;

import junit.framework.TestCase;
import main.Graph;

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
}
