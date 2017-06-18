package main;
import java.util.*;
import org.apache.log4j.Logger;
import java.io.*;
import utility.*;

// This Graph contains only undirected edges

public class Graph {

	/**
	 * This method takes in the name of the file and
	 * creates a Graph object from that file.
	 * @param filename
	 * @return a Graph object
	 */
	public static Graph createGraphFromFile(String filename) {
		String pathBase = "/Users/Ishmael/java/RandomContraction/";
		File file = new File(pathBase + filename);
		Graph g = new Graph();
		try {
			BufferedReader rd = new BufferedReader(new FileReader(file));
			String line = "";
			Map<String, Vertex> vertices = g.getVertices();
			while ((line = rd.readLine()) != null) {
				String[] numbers = line.split("\t");
				String id = numbers[0];
				Vertex v = (vertices.containsKey(id)) ? vertices.get(id) : new Vertex(id);
				for (int i = 1; i < numbers.length; i++) {
					id = numbers[i];
					if (!vertices.containsKey(id)) 
						vertices.put(id, new Vertex(id));
					v.add(vertices.get(id));
//					// for vertex with smaller label, they are already
//					// objects created, so don't create them again.
//					if (Integer.valueOf(numbers[i]) < Integer.valueOf(v.getID())) {
//						v.add(g.getVertices().get(numbers[i]));
//					} else
//						v.add(new Vertex(numbers[i]));
				}
				g.add(v);
			}
			rd.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return g;
	}
	
	public Graph() {
		setUp();
	}
	
	/**
	 * Add a new vertex to the graph.
	 * Every time the program adds a vertex into the graph
	 * we update the u_edges variable.
	 * We will look through the adjacent list of that vertex,
	 * for vertices whose label number is smaller than the root vertex,
	 * we skip it, otherwise we create a new edge and add it into
	 * the graph.
	 * NOTE: THIS IS ONLY THE CASE WHEN INITIALIZING!!
	 * @param v
	 */
	public void add(Vertex v) {
		vertices.put(v.getID(), v);
//		List<Vertex> adjacents = v.getAdjacentList();
		int root_id = 0;
		try {
			root_id = Integer.valueOf(v.getID());
		} catch (NumberFormatException e) {
			System.err.println("The id of this vertex is not pure number: " + v.getID()
					+ "\nThus unable to add vertex.");
			return;
		}
		for (Vertex vv : v.getAdjacentList()) {
			// here we assume the ids are all numbers
			int id = Integer.valueOf(vv.getID());
			if (id < root_id) continue;
			else {
				u_edges.add(new UndirectedEdge(vv, v));
			}
		}
	}
	
	/**
	 * This method merges two vertices.
	 * @param id1
	 * @param id2
	 */
	public void merge(String id1, String id2) {
		log.info("[Merge Method] Merging " + id1 + " and " + id2);
		String new_id = id1 + "-" + id2;
		Vertex v1 = vertices.get(id1);
		Vertex v2 = vertices.get(id2);
		Vertex new_vertex = new Vertex(new_id);
		if (v1 == null)
			System.out.println("\t!!! Found it. v1 is null!!!");
		List<Vertex> list = new ArrayList<Vertex>();
		// get the adjacent vertices of the new vertex right
		GraphUtility.addListToList(v1.getAdjacentList(), list);
		GraphUtility.addListToList(v2.getAdjacentList(), list);
		GraphUtility.removeDuplicates(list);
		GraphUtility.deleteVertexFromList(v1, list);
		GraphUtility.deleteVertexFromList(v2, list);
		new_vertex.setAdjacentList(list);
		list = new ArrayList<Vertex>();
		// get the components of the new vertex right
		GraphUtility.addListToList(v1.getComponents(), list);
		GraphUtility.addListToList(v2.getComponents(), list);
		new_vertex.setComponents(list);
		
		// remove the edge between them
		removeEdgeBetween(v1, v2);
		redirectEdge(v1, v2, new_vertex);
		// update adjacent's adjacent list
		updateAdjacents(v1, v2, new_vertex);
		
		// remove duplicates from the adjacent's adjacent list
		for (Vertex v : v1.getAdjacentList())
			GraphUtility.removeDuplicates(v.getAdjacentList());
		for (Vertex v : v2.getAdjacentList())
			GraphUtility.removeDuplicates(v.getAdjacentList());
		vertices.remove(id1);
		vertices.remove(id2);
		vertices.put(new_vertex.getID(), new_vertex);
		log.info("[Merge Method] Remove " + id1 + " and " + id2 
				+ " adding " + new_id);
	}
	
	public int getMinCut() {
		int count = 0;
		while (vertices.size() > 2) {
			log.info(++count + "-th iteration.");
			UndirectedEdge e = getRandomUEdge();
			merge(e.getVertex1().getID(), e.getVertex2().getID());
		}
		return u_edges.size();
	}
	
	/**
	 * @return a random edge from the graph
	 */
	public UndirectedEdge getRandomUEdge() {
		int upper_bound = u_edges.size();
		Random ran = new Random();
		UndirectedEdge result = u_edges.get(ran.nextInt(upper_bound));
		log.info("[Random Edge] Pick edge: " + result.getVertex1().getID() 
				+ " ---- " + result.getVertex2().getID());
		return result;
	}
	

	
	/**
	 * Redirect all the edges that used to link to v1 or v2
	 * to the new vertex, which is a combination of v1 and v2
	 * @param v1
	 * @param v2
	 * @param new_v
	 */
	private void redirectEdge(Vertex v1, Vertex v2, Vertex new_v) {
		//updateAdjacents(v1, v2, new_v);
		for (Vertex v : v1.getAdjacentList()) {
			for (UndirectedEdge e : getEdge(v, v1))
				e.moveOn(v1, new_v);
		}
		for (Vertex v : v2.getAdjacentList()) {
			for (UndirectedEdge e : getEdge(v, v2))
				e.moveOn(v2, new_v);
		}
	}
	
	/**
	 * Get an edge from the u_edges variable
	 * based on the two vertices provided
	 */
	private List<UndirectedEdge> getEdge(Vertex v1, Vertex v2) {
		List<UndirectedEdge> result = new ArrayList<UndirectedEdge>();
		UndirectedEdge e0 = new UndirectedEdge(v1, v2);
		for (UndirectedEdge e : u_edges) {
			if (e0.resembles(e)) result.add(e);
		}
		return result;
	}
	
	/**
	 * After merging two vertices, information on vertices about the edges that are
	 * related to these two should be updated.
	 * 
	 */
	private void updateAdjacents(Vertex v1, Vertex v2, Vertex new_v) {
		// variable list is a list of all the vertices that connects to
		// either v1 or v2.
		List<Vertex> list = new ArrayList<Vertex>();
		for (Vertex v : v1.getAdjacentList())
			list.add(v);
		for (Vertex v : v2.getAdjacentList())
			list.add(v);
		
		for (Vertex v : list) {
			List<Vertex> adjList = v.getAdjacentList();
			// help vertex in this adjList move on
			boolean finish = false;
			while (!finish) {
				finish = true;
				for (Vertex vv : adjList) {
					if (vv.equals(v1) || vv.equals(v2)) {
						finish = false;
						adjList.remove(vv);
						adjList.add(new_v);
						break;
					}
				}
			}
		}
		
	} 
	
	/**
	 * Remove all the edges between the two vertices
	 */
	public void removeEdgeBetween(Vertex v1, Vertex v2) {
		UndirectedEdge model = new UndirectedEdge(v1, v2);
		boolean finish = false;
		while (!finish) {
			finish = true;
			for (UndirectedEdge e : u_edges)
				if (e.resembles(model)) {
					u_edges.remove(e);
					finish = false;
					break;
				}
		}
	}
	
//	/**
//	 * Context: after two adjacent vertices are merged,
//	 * these two vertices are counted as adjacent vertices of
//	 * the combined new vertex. This method is meant to delete
//	 * the old two vertices from the adjacent list.
//	 * @param v
//	 */
//	private void removeInsideAdj(Vertex v) {
//		List<Vertex> components = v.getComponents();
//		List<Vertex> adjList = v.getAdjacentList();
//		List<Vertex> new_adjList = new ArrayList<Vertex>();
//		for (Vertex vv : adjList) {
//			if (!GraphUtility.listContainsVertex(components, vv)) 
//				new_adjList.add(vv);
//		}
//		v.setAdjacentList(new_adjList);
//	}
	
	/**
	 * I'm combining all the steps required to initiate 
	 * a graph object so that the constructor can be extensible.
	 */
	private void setUp() {
		vertices = new HashMap<String, Vertex>();
		u_edges = new ArrayList<UndirectedEdge>();
	}
	
	
	/********     The Getter and Setters     ***********/
	public Map<String, Vertex> getVertices() {
		return vertices;
	}
	
	public List<UndirectedEdge> getUndirectedEdge() {
		return u_edges;
	}
	
	/** The key for each vertex is its id */
	private Map<String, Vertex> vertices;
	
	private List<UndirectedEdge> u_edges;
	private static Logger log = Logger.getLogger(Graph.class);

}
