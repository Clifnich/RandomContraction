package main;

import java.util.*;

public class Vertex {

	/**
	 * The default constructor will generate a random id 
	 * for the vertex
	 */
	public Vertex() {
		id = UUID.randomUUID().toString();
		setUp();
	}
	
	public Vertex(String id) {
		this.id = id;
		setUp();
	}
	
	/**
	 * Two vertices are equal iff their ids are the same
	 * @param v
	 */
	public boolean equals(Vertex v) {
		return id.equals(v.getID());
	}
	
	/**
	 * Add a vertex to the adjacent list
	 * @param v
	 */
	public void add(Vertex v) {
		adjacentList.add(v);
	}
	
	public String getID() {
		return id;
	}
	
	public List<Vertex> getComponents() {
		return components;
	}
	
	public List<Vertex> getAdjacentList() {
		return adjacentList;
	}
	
	public void setAdjacentList(List<Vertex> adList) {
		adjacentList = adList;
	}
	
	public void setComponents(List<Vertex> comp) {
		components = comp;
	}
	
	private void setUp() {
		components = new ArrayList<Vertex>();
		components.add(this);
		adjacentList = new ArrayList<Vertex>();
	}
	
	private String id;
	
	/** This variable records the elements this vertex has combined 
	 * during the "merge" sessions */
	private List<Vertex> components;
	
	private List<Vertex> adjacentList;
}
