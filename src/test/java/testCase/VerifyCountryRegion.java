package testCase;

import java.io.IOException;
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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * The VerifyCountryRegion class is used for setting Country/region as Australia
 * @author Aarti
 *
 */
public class VerifyCountryRegion extends Base {

	private static final Logger logger = Logger.getLogger(VerifyCountryRegion.class.getName());
	public AndroidDriver<AndroidElement> driver;
	public WebDriverWait wait; 
	
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
		wait = new WebDriverWait(driver, 30);
	}

	/**
	 * The test case method will verify Country/region as Australia after changing
	 * the default Country setting.
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
		wait.until(ExpectedConditions.visibilityOf(wp.signIn));
		if (wp.signIn.isDisplayed()) {
			wp.signIn.click();
		} else
			System.out.print("In Home screen");
		logger.info("Home Page will display");
		wait.until(ExpectedConditions.visibilityOf(hp.sidePanel));
		// Thread.sleep(3000);
		Utilities.Click(hp.sidePanel);
		logger.info("hamburger menu will display");
		Utilities.Click(sp.setting);
		Utilities.Click(sp.countryLanguage);
		Utilities.Click(sp.countryRegion);

		try {
			if (!sp.ausRedioBtn.isSelected()) {
				wait.until(ExpectedConditions.visibilityOf(sp.ausRedioBtn));
				sp.ausRedioBtn.click();
			} else {
				WebElement ele = ut.ScrollToText("Australia");
				System.out.print(ele.getText());
				if (!sp.ausRedioBtn.isSelected()) {
					wait.until(ExpectedConditions.visibilityOf(sp.ausRedioBtn));
					sp.ausRedioBtn.click();
				}
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
