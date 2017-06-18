package junit;

import junit.framework.TestCase;
import main.*;
import java.util.*;
import utility.*;

public class FileTwoStepByStepTest extends TestCase {

	private Graph g;
	private Map<String, Vertex> map;
	
	/**
	 * Check to see if two lists are the same.
	 * Order of elements doesn't matter.
	 * @param list1
	 * @param list2
	 * @return
	 */
	private boolean equals(List<Vertex> list1, List<Vertex> list2) {
		if (list1.size() != list2.size())
			return false;
		for (Vertex v : list1) {
			if (!GraphUtility.listContainsVertex(list2, v))
				return false;
		}
		return true;
	}
	
	public FileTwoStepByStepTest(String name) {
		super(name);
		g = Graph.createGraphFromFile("two.txt");
	}
	
	/**
	 * Mainly to test the adjacent lists.
	 * They should be exactly as expected.
	 */
	public void testBeforeIter() {
		g = Graph.createGraphFromFile("two.txt");
		map = g.getVertices();
		Vertex v1 = map.get("1");
		Vertex v2 = map.get("2");
		Vertex v3 = map.get("3");
		Vertex v4 = map.get("4");
		Vertex v5 = map.get("5");
		Vertex v6 = map.get("6");
		Vertex v7 = map.get("7");
		Vertex v8 = map.get("8");
		List<Vertex> list = new ArrayList<Vertex>();
		list.add(v2);list.add(v3);list.add(v4);list.add(v7);
		assertTrue(equals(list, v1.getAdjacentList()));
		
		list = new ArrayList<Vertex>();
		list.add(v1);list.add(v3);list.add(v4);
		assertTrue(equals(list, v2.getAdjacentList()));
		
		list = new ArrayList<Vertex>();
		list.add(v1);list.add(v2);list.add(v4);
		assertTrue(equals(list, v3.getAdjacentList()));
		
		list = new ArrayList<Vertex>();
		list.add(v1);list.add(v2);list.add(v3);list.add(v5);
		assertTrue(equals(list, v4.getAdjacentList()));
		
		list = new ArrayList<Vertex>();
		list.add(v4);list.add(v6);list.add(v7);list.add(v8);
		assertTrue(equals(list, v5.getAdjacentList()));
		
		list = new ArrayList<Vertex>();
		list.add(v5);list.add(v7);list.add(v8);
		assertTrue(equals(list, v6.getAdjacentList()));
		
		list = new ArrayList<Vertex>();
		list.add(v5);list.add(v6);list.add(v8);list.add(v1);
		assertTrue(equals(list, v7.getAdjacentList()));
		
		list = new ArrayList<Vertex>();
		list.add(v5);list.add(v6);list.add(v7);
		assertTrue(equals(list, v8.getAdjacentList()));
	}
	
	public void testFirstIter() {
		g = Graph.createGraphFromFile("two.txt");
		g.merge("8", "7");
		map = g.getVertices();
		List<Vertex> list = new ArrayList<Vertex>();

		Vertex v1 = map.get("1");
		Vertex v2 = map.get("2");
		Vertex v3 = map.get("3");
		Vertex v4 = map.get("4");
		Vertex v5 = map.get("5");
		Vertex v6 = map.get("6");
		Vertex v87 = map.get("8-7");
		
		list = new ArrayList<Vertex>();
		list.add(v2);list.add(v3);list.add(v4);list.add(v87);
		assertTrue(equals(list, v1.getAdjacentList()));
		
		// v2 adjacent to v1, v3, v4
		list = new ArrayList<Vertex>();
		list.add(v1);list.add(v3);list.add(v4);
		assertTrue(equals(list, v2.getAdjacentList()));
		
		// v3 adjacent to v1, v2, v4
		list = new ArrayList<Vertex>();
		list.add(v1);list.add(v2);list.add(v4);
		assertTrue(equals(list, v3.getAdjacentList()));
		
		// v4 adjacent to v1, v2, v3, v5
		list = new ArrayList<Vertex>();
		list.add(v1);list.add(v2);list.add(v3);list.add(v5);
		assertTrue(equals(list, v4.getAdjacentList()));
		
		// v5 adjacent to v4, v87, v6
		list = new ArrayList<Vertex>();
		list.add(v4);list.add(v87);list.add(v6);
		assertTrue(equals(list, v5.getAdjacentList()));
		
		// v6 adjacent to v5, v87
		list = new ArrayList<Vertex>();
		list.add(v5);list.add(v87);
		assertTrue(equals(list, v6.getAdjacentList()));
		
		// v87 adjacent to v1, v5, v6
		list = new ArrayList<Vertex>();
		list.add(v1);list.add(v5);list.add(v6);
		assertTrue(equals(list, v87.getAdjacentList()));
	}
	
	public void testSecondIter() {
		g = Graph.createGraphFromFile("two.txt");
		g.merge("8", "7");
		g.merge("2", "1");
		map = g.getVertices();
		Vertex v21 = map.get("2-1");
		if (v21 == null) System.out.println("v21 is null");
		Vertex v3 = map.get("3");
		Vertex v4 = map.get("4");
		Vertex v5 = map.get("5");
		Vertex v6 = map.get("6");
		Vertex v87 = map.get("8-7");
		
		// v21 adjacent to v3, v4, v87
		List<Vertex> list = new ArrayList<Vertex>();
		list.add(v3);list.add(v4);list.add(v87);
		assertTrue(equals(list, v21.getAdjacentList()));
		
		// v3 adjacent to v21, v4
		list = new ArrayList<Vertex>();
		list.add(v21);list.add(v4);
		assertTrue(equals(list, v3.getAdjacentList()));
		
		// v4 adjacent to v21, v3, v5
		list = new ArrayList<Vertex>();
		list.add(v21);list.add(v3);list.add(v5);
		assertTrue(equals(list, v4.getAdjacentList()));
		
		// v5 adjacent to v87, v6, v4
		list = new ArrayList<Vertex>();
		list.add(v87);list.add(v6);list.add(v4);
		assertTrue(equals(list, v5.getAdjacentList()));
		
		// v6 adjacent to v87, v5
		list = new ArrayList<Vertex>();
		list.add(v87);list.add(v5);
		assertTrue(equals(list, v6.getAdjacentList()));
		
		// v87 adjacent to v21, v5, v6
		list = new ArrayList<Vertex>();
		list.add(v21);list.add(v5);list.add(v6);
		assertTrue(equals(list, v87.getAdjacentList()));
	}
}
