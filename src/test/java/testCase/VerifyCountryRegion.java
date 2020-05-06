package testCase;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import assertions.Compare;
import base.Base;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import pageObjects.HomePage;
import pageObjects.HamburgerMenu;
import pageObjects.WelcomePage;
import utilities.Utilities;

/**
 * The VerifyCountryRegion class is used for setting Country/region as Australia
 * @author Aarti
 *
 */
public class VerifyCountryRegion extends Base {
	
	private static final Logger logger = Logger.getLogger(VerifyCountryRegion.class.getName());
	
	public AndroidDriver<AndroidElement> driver;
	
	/**
	 * The method is use for starting the server and initiating the driver .
	 * @throws InterruptedException
	 * @throws IOException
	 */
	@BeforeTest
	public void initialization() throws InterruptedException, IOException {
		logger.info("Starting server");
		service = StartServer();
		logger.info("Connecting with device");
		driver = Capabilities("AmazonApplication");
	}

	/**
	 * The test case method will verify Country/region as Australia after changing the default Country setting.
	 * @throws InterruptedException
	 * @throws IOException
	 */
	@Test
	public void verifyingCountry() throws InterruptedException, IOException {	
		WelcomePage wp = new WelcomePage(driver);
		HomePage hp = new HomePage(driver);
		HamburgerMenu sp = new HamburgerMenu(driver);
		Utilities ut = new Utilities(driver);
		logger.info("Welcome Page will display");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		if (wp.signIn.isDisplayed()) {
			wp.signIn.click();
		} else
			System.out.print("In Home screen");
		logger.info("Home Page will display");
		Thread.sleep(3000);
		Utilities.Click(hp.searchPlate);
		logger.info("hamburger menu will display");
		Utilities.Click(sp.setting);
		Utilities.Click(sp.countryLanguage);
		Utilities.Click(sp.countryRegion);

		try {
			if (!sp.ausRedioBtn.isSelected()) {
				sp.ausRedioBtn.click();
				Thread.sleep(3000);
			} else {
				WebElement ele = ut.ScrollToText("Australia");
				System.out.print(ele.getText());
				if (!sp.ausRedioBtn.isSelected())
					sp.ausRedioBtn.click();
				Thread.sleep(3000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Country Verification
		String Country = sp.GetCountryNameText(driver);
		boolean flag = Compare.assertEquals(Country, "Australia");
		if (flag)
			System.out.println("Verified Country...");
		else
			Compare.assertFail();
	}
	
	/**
	 * The method is use for stopping the server and closing the driver.
	 * @throws InterruptedException
	 * @throws IOException
	 */
	@AfterTest
	public void endSession() throws InterruptedException, IOException {
		logger.info("closing driver");
		closeDriver();
		logger.info("stopping server");
		service.stop();
	}
}
