package junit;

import junit.framework.TestCase;
import java.util.*;
import main.*;
import utility.GraphUtility;

public class TestGraphUtility extends TestCase {

	public TestGraphUtility(String name) {
		super(name);
	}
	
	/**
	 * Test that removeDuplicates work as expected
	 */
	public void testRemoveD() {
		List<Vertex> list = new ArrayList<Vertex>();
		Vertex v1 = new Vertex("1");
		list.add(v1);list.add(v1);
		GraphUtility.removeDuplicates(list);
		assertEquals(1, list.size());
		assertTrue(list.get(0).equals(v1));
	}
	
	/**
	 * Test deleteVertexFromList method
	 */
	public void testDeleteVertex() {
		List<Vertex> list = new ArrayList<Vertex>();
		Vertex v1 = new Vertex("1");
		list.add(v1);
		GraphUtility.deleteVertexFromList(v1, list);
		assertEquals(0, list.size());
	}
	
	/**
	 * Test deleteVertexFromList can delete multiply vertices
	 */
	public void testDeleteMultiply() {
		List<Vertex> list = new ArrayList<Vertex>();
		Vertex v1 = new Vertex("2");
		list.add(v1);list.add(v1);
		GraphUtility.deleteVertexFromList(v1, list);
		assertEquals(0, list.size());
	}
}
