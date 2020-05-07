package testCase;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.ResourceBundle;
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
import pageObjects.SettingsMenuPage;
import pageObjects.CountryRegionLanguagePage;
import pageObjects.HamburgerMainMenu;
import pageObjects.WelcomePage;
import utilities.Utilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * The VerifyCountryRegion class is used for setting Country/region as Australia
 * 
 * @author Aarti
 *
 */
public class VerifyCountryRegion extends Base {

	private static final Logger logger = Logger.getLogger(VerifyCountryRegion.class.getName());
	public AndroidDriver<AndroidElement> driver;
	public WebDriverWait wait;
	public ResourceBundle global;

	/**
	 * The method is use for starting the server and initiating the driver .
	 * 
	 * @throws InterruptedException
	 * @throws IOException
	 */
	@BeforeTest
	public void initialization() throws InterruptedException, IOException {
		logger.info("Starting server");
		service = StartServer();
		logger.info("Connecting with device");
		driver = Capabilities("amazonApplication");
		wait = new WebDriverWait(driver, 30);
		global = ResourceBundle.getBundle("global");
	}

	/**
	 * The test case method will verify Country/region as Australia after changing
	 * the default Country setting.
	 * 
	 * @throws InterruptedException
	 * @throws IOException
	 */
	@Test
	public void verifyingCountry() throws InterruptedException, IOException {
		WelcomePage wp = new WelcomePage(driver);
		HomePage hp = new HomePage(driver);
		HamburgerMainMenu sp = new HamburgerMainMenu(driver);
		Utilities ut = new Utilities(driver);
		SettingsMenuPage sm = new SettingsMenuPage(driver);
		CountryRegionLanguagePage crl = new CountryRegionLanguagePage(driver);

		logger.info("Welcome Page will display");
		wait.until(ExpectedConditions.visibilityOf(wp.signIn));
		boolean eleDisplay = Compare.isElementDisplay(wp.signIn);
		if (eleDisplay) {
			wp.signIn.click();
		} else
			System.out.print("In Home screen");
		logger.info("Home Page will display");
		wait.until(ExpectedConditions.visibilityOf(hp.sidePanel));
		boolean homePageDisplay = HomePage.verifyingHomePage(hp.homePageSearch,
				global.getString("homePageTitle"));
		assertEquals(homePageDisplay, true);
		Utilities.Click(hp.sidePanel);
		logger.info("Main menu will display");
		boolean hamBMDisplay = HamburgerMainMenu.verifyingHamburgerMenu(sp.hamburgerMenu,
				global.getString("hamburgerMenuTitle"));
		assertEquals(hamBMDisplay, true);
		Utilities.Click(sp.setting);
		logger.info("Settings menu page will display");
		boolean settingDisplay = SettingsMenuPage.verifyingSettingsMenu(sm.settingsMenu,
				global.getString("settingsMenuTitle"));
		assertEquals(settingDisplay, true);
		Utilities.Click(sm.countryLanguage);
		logger.info("Country/Region & Language page will display");
		boolean ConLangDisplay = CountryRegionLanguagePage.verifyingCountryRegionLang(crl.countryRegionLang,
				global.getString("conRegLangTitle"));
		assertEquals(ConLangDisplay, true);
		Utilities.Click(crl.countryRegion);
		logger.info("Country options will display with selected language");
		boolean ConViewDisplay = CountryRegionLanguagePage.verifyingCountryRegionLang(crl.countryRegionView,
				global.getString("conRegView"));
		assertEquals(ConViewDisplay, true);

		try {
			if (!sp.ausRedioBtn.isSelected()) {
				wait.until(ExpectedConditions.visibilityOf(sp.ausRedioBtn));
				sp.ausRedioBtn.click();
			} else {
				WebElement ele = ut.ScrollToText(global.getString("country"));
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
		String Country = crl.getCountryNameText(driver);
		boolean flag = Compare.assertEquals(Country, "Australia");
		if (flag)
			System.out.println("Verified Country...");
		else
			Compare.assertFail();
	}

	/**
	 * The method is use for stopping the server and closing the driver.
	 * 
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
