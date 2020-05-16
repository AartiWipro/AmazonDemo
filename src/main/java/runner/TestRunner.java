package runner;

import java.util.ArrayList;
import java.util.List;

import org.testng.TestNG;

public class TestRunner {

	public static void rerunFailedTest(String testCase) {
     
		System.out.println("Running failed test case again: " + testCase);

		TestNG runner = new TestNG();
		List<String> list = new ArrayList<String>();
		list.add(System.getProperty("user.dir") + "\\test-output\\TestSuite\\testng-failed.xml");
		runner.setTestSuites(list);
		runner.run();
	}
}
