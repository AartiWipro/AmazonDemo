package resources;

import java.io.IOException;

import org.testng.ITestListener;
import org.testng.ITestResult;

import base.Base;

/**
 * The Listeners class contains all the executed test cases status
 * @author Aarti
 *
 */
public class Listeners implements ITestListener {

	/**
	 * The onTestFailure method is use to check the screen shot for executed fail test cases status
	 */
	@Override
	public void onTestFailure(ITestResult result) {
		String sc = result.getName();
		try {
			Base.GetScreenShot(sc);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
