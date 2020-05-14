package resources;

import java.io.IOException;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import base.TestBase;

/**
 * The Listeners class contains all the executed test cases status
 * 
 * @author Aarti
 *
 */
public class Listeners implements ITestListener {

	/**
	 * The onTestFailure method is use to check the screen shot for executed fail
	 * test cases status
	 */
	@Override
	public void onTestFailure(ITestResult result) {
		String sceenShot = result.getName();
		try {
			TestBase.captureScreenShot(sceenShot);
			ITestListener.super.onTestFailure(result);
			System.out.println("TestCase failed and details are : " + result.getName());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("TestCase Started and details are : " + result.getName());
		ITestListener.super.onTestStart(result);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("TestCase Success and details are : " + result.getName());
		ITestListener.super.onTestSuccess(result);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("TestCase Skipped and details are : " + result.getName());
		ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onFinish(context);
	}
}
