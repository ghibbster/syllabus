package assessEvosuite;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.evosuite.EvoSuite;
import org.junit.Assert;
import org.junit.Test;

public class Assessment {
	
	public static void main (String [] args){
		new Assessment().testEvo();
	}
	
	public void testEvo(){
		
		String user_dir = System.getProperty("user.dir");
		
		Path dep_1 = Paths.get(user_dir, "target" , "assess.jar");
		String dependency_1 = dep_1.toString();
		
		Path testpath = Paths.get(user_dir, "evo-tests");
		String test_path = testpath.toString();
		
		String[] command = {
				"-Dtest_dir="+ test_path, // where the test suite is written
				"-Dminimize=TRUE", // remove test cases that do not contribute to the coverage after search is over
				"-Dpopulation=50", // number of test suites in a population
				"-Dsearch_budget=60", // 60 seconds to search
				/**
				 * BRANCH and LINE criteria are set for  now.
				 * Consider using other criteria such as CBRANCH
				 * You can see what other criteria are implemented in "Properties.java" in EvoSuite
				 * https://github.com/EvoSuite/evosuite/blob/1.0.3/client/src/main/java/org/evosuite/Properties.java
				 */
				"-Dcriterion=BRANCH:LINE", // optimize for branch and line criteria
				"-projectCP",
				dependency_1, //either a jar or a folder where class files exist
				"-class", 
				"assessEvosuite.FlagChecker" // qualified name of the target class
				};
		
		EvoSuite evosuite = new EvoSuite();
		Object result = evosuite.parseCommandLine(command);
	}


}
