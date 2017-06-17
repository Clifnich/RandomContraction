package junit;

import junit.framework.TestResult;
import junit.framework.TestSuite;

public class MyTestSuite {

	public static void main(String[] args) {
		TestSuite suite = new TestSuite();
		suite.addTest(new EdgeTests("2"));
		suite.addTest(new GraphEdgeTest("1"));
		suite.addTest(new GraphVertexTest("1"));
		suite.addTest(new TestGraph("1"));
		suite.addTest(new VertexTests("1"));
		junit.textui.TestRunner.runAndWait(suite);
		//suite.run(new TestResult());
	}
}
