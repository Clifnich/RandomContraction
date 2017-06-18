package junit;

import junit.framework.TestCase;
import main.Graph;

public class SeparateTest extends TestCase {

	public SeparateTest(String name) {
		super(name);
	}
	
	public void testMinCut0() {
		Graph g = Graph.createGraphFromFile("two2.txt");
		int min = g.getMinCut();
		for (int i = 0; i < Math.pow(g.getVertices().size(), 2); i++) {
			int result = g.getMinCut();
			if (result < min) min = result;
		}
		assertEquals(2, min);
	}
}
