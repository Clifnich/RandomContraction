package main;
import java.util.*;

import org.apache.log4j.Logger;

import java.io.*;

// This Graph contains only undirected edges

public class Graph {

	static Logger log = Logger.getLogger(Graph.class);
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
			while ((line = rd.readLine()) != null) {
				String[] numbers = line.split(" ");
				Vertex v = new Vertex(numbers[0]);
				for (int i = 1; i < numbers.length; i++) {
					// for vertex with smaller label, they are already
					// objects created, so don't create them again.
					if (Integer.valueOf(numbers[i]) < Integer.valueOf(v.getID())) {
						v.add(g.getVertices().get(numbers[i]));
					} else
						v.add(new Vertex(numbers[i]));
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
	 * Add a new vertex to the graph
	 * @param v
	 */
	public void add(Vertex v) {
		vertices.put(v.getID(), v);
		updateUEdges(v);
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
		for (Vertex v : v1.getAdjacentList()) 
			list.add(v);
		for (Vertex v : v2.getAdjacentList()) 
			list.add(v);
		new_vertex.setAdjacentList(list);
		list = new ArrayList<Vertex>();
		for (Vertex v : v1.getComponents())
			list.add(v);
		for (Vertex v : v2.getComponents())
			list.add(v);
		new_vertex.setComponents(list);
		
		// remove the edge between them
		removeEdge(v1, v2);
		removeSelfloop(new_vertex);
		updateEdge(v1, v2, new_vertex);
		updateAdjacents(v1, v2, new_vertex);

		vertices.remove(id1);
		vertices.remove(id2);
		vertices.put(new_id, new_vertex);	
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
	
	public Map<String, Vertex> getVertices() {
		return vertices;
	}
	
	public List<UndirectedEdge> getUndirectedEdge() {
		return u_edges;
	}
	
	/**
	 * Redirect all the edges that used to link to v1 or v2
	 * to the new vertex, which is a combination of v1 and v2
	 * @param v1
	 * @param v2
	 * @param new_v
	 */
	private void updateEdge(Vertex v1, Vertex v2, Vertex new_v) {
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
//			List<Integer> deleteList = new ArrayList<Integer>();
//			for (int i = 0; i < adjList.size(); i++) {
//				Vertex vv = adjList.get(i);
//				if (vv.equals(v1) || vv.equals(v2)) 
//					deleteList.add(i);
//			}
//			for (int i = 0; i < deleteList.size(); i++)
//				adjList.add(new_v);
//			for (Integer i : deleteList)
//				adjList.remove(i.intValue());
		}
		
	} 
	
	/**
	 * Remove the edge between two vertices
	 */
	public void removeEdge(Vertex v1, Vertex v2) {
		UndirectedEdge model = new UndirectedEdge(v1, v2);
//		List<Integer> deleteList = new ArrayList<Integer>();
//		for (int i = 0; i < u_edges.size(); i++) {
//			UndirectedEdge e = u_edges.get(i);
//			if (e.resembles(model)) 
//				deleteList.add(i);
//		}
//		for (Integer i : deleteList)
//			u_edges.remove(i.intValue());
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
	
	private void removeSelfloop(Vertex v) {
		List<Vertex> components = v.getComponents();
		List<Vertex> adjList = v.getAdjacentList();
		List<Vertex> new_adjList = new ArrayList<Vertex>();
		for (Vertex vv : adjList) {
			if (!listContainsVertex(components, vv)) 
				new_adjList.add(vv);
		}
		v.setAdjacentList(new_adjList);
	}
	
	/**
	 * Check to see if the vertex list has a certain vertex or not
	 * @param list
	 * @param v
	 * @return whether contains or not
	 */
	private boolean listContainsVertex(List<Vertex> list, Vertex v) {
		for (Vertex vv : list)
			if (vv.equals(v))
				return true;
		return false;
	}
	
	/**
	 * Every time the program adds a vertex into the graph
	 * we update the u_edges variable.
	 * We will look through the adjacent list of that vertex,
	 * for vertices whose label number is smaller than the root vertex,
	 * we skip it, otherwise we create a new edge and add it into
	 * the graph.
	 * @param v1
	 */
	private void updateUEdges(Vertex v1) {
		List<Vertex> adjacents = v1.getAdjacentList();
		int root_id = 0;
		try {
			root_id = Integer.valueOf(v1.getID());
		} catch (NumberFormatException e) {
			System.err.println("The id of this vertex is not pure number: " + v1.getID()
					+ "\nThus unable to add vertex.");
			return;
		}
		for (Vertex v : adjacents) {
			// here we assume the ids are all numbers
			int id = Integer.valueOf(v.getID());
			if (id < root_id) continue;
			else {
				u_edges.add(new UndirectedEdge(v, v1));
			}
		}
	}
	
	/**
	 * I'm combining all the steps required to initiate 
	 * a graph object so that the constructor can be extensible.
	 */
	private void setUp() {
		vertices = new HashMap<String, Vertex>();
		u_edges = new ArrayList<UndirectedEdge>();
	}
	
	/** The key for each vertex is its id */
	private Map<String, Vertex> vertices;
	
	private List<UndirectedEdge> u_edges;
}
