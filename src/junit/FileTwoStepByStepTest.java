package junit;

import junit.framework.TestCase;
import main.*;
import java.util.*;

public class FileTwoStepByStepTest extends TestCase {

	private Graph g;
	private Map<String, Vertex> map;
	
	/**
	 * Check to see if a vertex is in the list.
	 * The comparison criteria is id.
	 * @param v
	 * @param list
	 * @return
	 */
	private boolean vertexInList(Vertex v, List<Vertex> list) {
		for (Vertex vv : list)
			if (vv.equals(v))
				return true;
		return false;
	}
	
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
			if (!vertexInList(v, list2))
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
		
		
	}
}
