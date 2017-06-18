package junit;

import junit.framework.TestCase;
import main.*;
import java.util.*;

public class VertexTests extends TestCase {

	public VertexTests(String name) {
		super(name);
	}
	
	/**
	 * Calling the empty constructor
	 * should successfully instantiate one instance
	 */
	public void testInstantiate() {
		Vertex v = new Vertex();
		assertTrue(v != null);
	}
	
	/**
	 * A distinct Vertex instance should have a 
	 * unique identity.
	 */
	public void testID() {
		String id = "9527";
		Vertex v = new Vertex(id);
		assertEquals(v.getID(), id);
	}
	
	/**
	 * The two vertices are equal iff their identities 
	 * are the same.
	 * The equals() method should function just like that.
	 */
	public void testVertexEquals() {
		String id1 = "1234";
		String id2 = "2345";
		Vertex v1 = new Vertex(id1);
		Vertex v2 = new Vertex(id1);
		assertTrue(v1.equals(v2));
		
		Vertex v3 = new Vertex(id2);
		assertFalse(v3.equals(v1));
	}
	
	/**
	 * When the object is instantiated,
	 * it should properly instantiate the components element
	 * which is an array list of all the vertices it has 
	 * combined along the way.
	 * This testInitialComponents() tests that when instantiating,
	 * the vertex successfully counts itself as a member of 
	 * the components.
	 */
	public void testInitialComponents() {
		Vertex v = new Vertex();
		List<Vertex> expectedList = new ArrayList<Vertex>();
		expectedList.add(v);
		List<Vertex> components = v.getComponents();
		if (expectedList.size() != components.size())
			fail("Size doesn't match");
		else {
			for (int i = 0; i < expectedList.size(); i++) {
				if (!expectedList.get(i).equals(components.get(i)))
					fail("Mismatch vertex");
			}
		}
	}
	
	/**
	 * When the object is instantiated,
	 * it should have an empty adjacent list.
	 */
	public void testInitialAdjacentList() {
		Vertex v = new Vertex();
		assertTrue(v.getAdjacentList().size() == 0);
	}
	
	/**
	 * Test adding an adjacent vertex to an existing one
	 */
	public void testAdd2AdjacentList() {
		String id1 = "ooo";
		String id2 = "zzz";
		Vertex v1 = new Vertex(id1);
		Vertex v2 = new Vertex(id2);
		v1.add(v2);
		assertEquals(v1.getAdjacentList().size(), 1);
		assertTrue(v1.getAdjacentList().get(0).equals(v2));
	}
	
	/**
	 * Test the moveOn method
	 */
	public void testMoveOnMethod() {
		Vertex v1 = new Vertex("1");
		Vertex v2 = new Vertex("2");
		Vertex v3 = new Vertex("3");
		
		v1.add(v2);
		v1.moveOn(v2, v3);
		List<Vertex> list = v1.getAdjacentList();
		assertEquals(1, list.size());
		assertTrue(list.get(0).equals(v3));
	}
	
	/**
	 * Test adjacent's adjacent
	 */
	public void testDoubleAdjacent() {
		Vertex v1 = new Vertex("1");
		Vertex v2 = new Vertex("2");
		v1.add(v2);
		v2.add(v1);
		assertTrue(v1.getAdjacentList().get(0).getAdjacentList().get(0).equals(v1));
	}
	

}
