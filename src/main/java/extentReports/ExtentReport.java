package extentReports;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

/**
 * The ExtentReportNG class is used for creating Extent report with all test case status.
 * @author Aarti
 *
 */
public class ExtentReport implements IReporter {
	private ExtentReports extent;
	ExtentHtmlReporter htmlReporter;

	/**
	 * The generateReport method is used for generating the Extent report
	 * @param <XmlSuite> : will define List<XmlSuite> value
	 * @param XmlSuite : will define tag <suite> value
	 * @param suites : will define List<ISuite> value
	 * @param outputDirectory : will define String value
	 */
	   @Override
	   public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
		htmlReporter = new ExtentHtmlReporter(new File(System.getProperty("user.dir") + "\\reports\\result.html"));		
		extent = new ExtentReports();

		extent.attachReporter(htmlReporter);
		for (ISuite suite : suites) {
			Map<String, ISuiteResult> result = suite.getResults();

			for (ISuiteResult r : result.values()) {
				ITestContext context = r.getTestContext();

				buildTestModes(context.getPassedTests(), Status.PASS);
				buildTestModes(context.getPassedTests(), Status.FAIL);
				buildTestModes(context.getPassedTests(), Status.SKIP);
			}
		}
		extent.flush();
	}

	/**
	 * The buildTestModes method will create the build for generateReport method. 
	 * @param tests : will define IResultMap value
	 * @param status : will define Status value
	 */
	private void buildTestModes(IResultMap tests, Status status) {
		ExtentTest test;
		if (tests.size() > 0) {
			for (ITestResult result : tests.getAllResults()) {
				test = extent.createTest(result.getMethod().getMethodName());

				for (String group : result.getMethod().getGroups())
					test.assignCategory(group);

				String message = "Test " + status.toString().toLowerCase() + "ed";
				if (result.getThrowable() != null)
					message = result.getThrowable().getMessage();
				test.log(status, message);
			}
		}
	}
}
