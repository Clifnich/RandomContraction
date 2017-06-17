package main;

public class UndirectedEdge extends Edge{

	public UndirectedEdge(Vertex v1, Vertex v2) {
		this.v1 = v1;
		this.v2 = v2;
	}
	
	/**
	 * Two edges are considered "alike" as long as
	 * they have the same vertices
	 * @param edge
	 * @return
	 */
	public boolean resembles(UndirectedEdge edge) {
		Vertex other_v1 = edge.getVertex1();
		Vertex other_v2 = edge.getVertex2();
		return (other_v1.equals(v1) && other_v2.equals(v2))
				|| (other_v1.equals(v2) && other_v2.equals(v1));
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
