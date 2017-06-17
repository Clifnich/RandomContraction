package main;

public class UndirectedEdge extends Edge{

	public UndirectedEdge(Vertex v1, Vertex v2) {
		this.v1 = v1;
		this.v2 = v2;
	}
	
	public Vertex getVertex1() {
		return v1;
	}
	
	public Vertex getVertex2() {
		return v2;
	}
	
	private Vertex v1;
	private Vertex v2;
}
