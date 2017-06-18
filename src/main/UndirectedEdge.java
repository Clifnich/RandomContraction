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
	
	/**
	 * This method changes one side of the edge to another vertex
	 * @return
	 */
	public void moveOn(Vertex old_vex, Vertex new_vex) {
		if (v1.equals(old_vex))
			v1 = new_vex;
		else if (v2.equals(old_vex))
			v2 = new_vex;
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
