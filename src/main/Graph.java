package main;
import java.util.*;
import java.io.*;

// This Graph contains only undirected edges

public class Graph {

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
				for (int i = 1; i < numbers.length; i++)
					v.add(new Vertex(numbers[i]));
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
	
	public void add(Vertex v) {
		vertices.put(v.getID(), v);
		updateUEdges(v);
	}
	
	public void merge(String id1, String id2) {
		String new_id = id1 + "-" + id2;
		Vertex v1 = vertices.get(id1);
		Vertex v2 = vertices.get(id2);
		Vertex new_vertex = new Vertex(new_id);
		new_vertex.setAdjacentList(v1.getAdjacentList());
		for (Vertex v : v2.getAdjacentList()) {
			new_vertex.add(v);
		}
		List<Vertex> components = v1.getComponents();
		for (Vertex v : v2.getComponents())
			components.add(v);
		new_vertex.setComponents(components);
		removeSelfloop(new_vertex);
		vertices.remove(id1);
		vertices.remove(id2);
		vertices.put(new_id, new_vertex);	
	}
	
	public UndirectedEdge getRandomUEdge() {
		int upper_bound = u_edges.size();
		Random ran = new Random();
		return u_edges.get(ran.nextInt(upper_bound));
	}
	
	public Map<String, Vertex> getVertices() {
		return vertices;
	}
	
	public List<UndirectedEdge> getUndirectedEdge() {
		return u_edges;
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
	
	private void setUp() {
		vertices = new HashMap<String, Vertex>();
		u_edges = new ArrayList<UndirectedEdge>();
	}
	
	/** The key for each vertex is its id */
	private Map<String, Vertex> vertices;
	
	private List<UndirectedEdge> u_edges;
}
