package junit;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class RunAllTests extends TestCase 
{
  @SuppressWarnings("rawtypes")
public static Test suite() 
  {
    try {
      Class[]  testClasses = {
        /*  Add the names of your unit test classes here */
        // Class.forName("your.class.name.here") 
    		  Class.forName("junit.EdgeTests"),
    		  Class.forName("junit.GraphEdgeTest"),
    		  Class.forName("junit.GraphVertexTest"),
    		  Class.forName("junit.TestGraph"),
    		  Class.forName("junit.VertexTests"),
    		  Class.forName("junit.FileTwoStepByStepTest"),
    		  Class.forName("junit.TestGraphUtility")
      };   
      
      return new TestSuite(testClasses);
    } catch(Exception e){
      e.printStackTrace();
    } 
    
    return null;
  }
}
