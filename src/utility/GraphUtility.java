package utility;

import java.util.*;
import main.*;

public class GraphUtility {

	/**
	 * Check to see if the vertex list has a certain vertex or not
	 * @param list
	 * @param v
	 * @return whether contains or not
	 */
	public static boolean listContainsVertex(List<Vertex> list, Vertex v) {
		for (Vertex vv : list)
			if (vv.equals(v))
				return true;
		return false;
	}
	
	/**
	 * Add all the members in list1 to list2
	 * @param list1
	 * @param list2
	 */
	public static void addListToList(List<Vertex> list1, List<Vertex> list2) {
		for (Vertex v : list1)
			list2.add(v);
	}
	
	/**
	 * Remove duplicate elements
	 * O(n^2) time
	 */
	public static void removeDuplicates(List<Vertex> list) {
		List<Vertex> result = new ArrayList<Vertex>();
		for (Vertex v : list)  {
			if (!listContainsVertex(result, v))
				result.add(v);
		}
		list.clear();
		for (Vertex v : result)
			list.add(v);
	}
	
	/**
	 * Delete a vertex from the list,
	 * such vertex can appeal multiply times in the list
	 */
	public static void deleteVertexFromList(Vertex v, List<Vertex> list) {
		List<Vertex> list2 = new ArrayList<Vertex>();
		for (Vertex vv : list) {
			if (!vv.equals(v))
				list2.add(vv);
		}
		list.clear();
		for (Vertex vv : list2)
			list.add(vv);
	}
}
