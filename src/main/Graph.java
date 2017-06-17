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
	}
	
	public Map<String, Vertex> getVertices() {
		return vertices;
	}
	
	public List<UndirectedEdge> getUndirectedEdge() {
		return u_edges;
	}
	
	private void setUp() {
		vertices = new HashMap<String, Vertex>();
		u_edges = new ArrayList<UndirectedEdge>();
	}
	
	/** The key for each vertex is its id */
	private Map<String, Vertex> vertices;
	
	private List<UndirectedEdge> u_edges;
}
