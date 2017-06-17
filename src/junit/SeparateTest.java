package junit;

import junit.framework.TestCase;
import main.Graph;

public class SeparateTest extends TestCase {

	public SeparateTest(String name) {
		super(name);
	}
	
	public void testMinCut0() {
		Graph g = Graph.createGraphFromFile("two.txt");
		assertEquals(2, g.getMinCut());
	}
}
