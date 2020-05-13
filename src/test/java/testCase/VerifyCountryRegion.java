package testCase;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.ResourceBundle;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import assertions.Validation;
import base.Base;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import pageObjects.HomePage;
import pageObjects.SettingsMenu;
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
	WelcomePage web;
	HomePage home;
	HamburgerMainMenu mainMenu;
	Utilities util;
	SettingsMenu setMenu;
	CountryRegionLanguagePage countryLang;

	/**
	 * The method is use for starting the server,  driver and initializing the object for all classes
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
		web = new WelcomePage(driver);
		home = new HomePage(driver);
		mainMenu = new HamburgerMainMenu(driver);
		setMenu = new SettingsMenu(driver);
		countryLang = new CountryRegionLanguagePage(driver);
	}

	/**
	 * The test case  will select Country/region as Australia after changing
	 * the default Country setting.
	 * 
	 * @throws InterruptedException
	 * @throws IOException
	 */
	@Test(priority = 0)
	public void setectCountry() throws InterruptedException, IOException {
		logger.info("Welcome Page will display");
		wait.until(ExpectedConditions.visibilityOf(web.signIn));
		boolean eleDisplay = Validation.isElementDisplay(web.signIn);
		if (eleDisplay) {
			web.signIn.click();
		} else
			System.out.print("In Home screen");
		logger.info("Home Page will display");
		wait.until(ExpectedConditions.visibilityOf(home.sidePanel));
		boolean homePageDisplay = Utilities.verifyingPage(home.homePageSearch, global.getString("homePageTitle"));
		assertEquals(homePageDisplay, true);
		Utilities.Click(home.sidePanel);
		logger.info("Main menu will display");
		boolean mainMenuDisplay = Utilities.verifyingPage(mainMenu.hamburgerMenu,
				global.getString("hamburgerMenuTitle"));
		assertEquals(mainMenuDisplay, true);
		HamburgerMainMenu.selectMainMenuOption(global.getString("selectedMainMenuOption"));
		logger.info("Settings menu page will display");
		boolean settingDisplay = Utilities.verifyingPage(setMenu.settingsMenu,
				global.getString("settingsMenuTitle"));
		assertEquals(settingDisplay, true);
		SettingsMenu.selectSettingMenuOption(global.getString("selectedSettingsMenuOption"));
		logger.info("Country/Region & Language page will display");
		boolean ConLangDisplay = Utilities.verifyingPage(countryLang.countryRegionLang,
				global.getString("conRegLangTitle"));
		assertEquals(ConLangDisplay, true);
		CountryRegionLanguagePage.selectConLanButton(global.getString("selectedConLanButton"));
		logger.info("Country options will display with selected language");
		boolean conViewDisplay = Utilities.verifyingPage(countryLang.countryRegionView,
				global.getString("conRegView"));
		assertEquals(conViewDisplay, true);
	}

	/**
	 * The test case will verify Country/region as Australia
	 * 
	 * @throws InterruptedException
	 * @throws IOException
	 */
	@Test(priority = 1)
	public void verifyingCountry() throws InterruptedException, IOException {
		CountryRegionLanguagePage.selectCountry(global.getString("country"), driver);
		// Country Verification
		String countryTxt = countryLang.getCountryNameText(driver);
		boolean txtDisplay = Validation.isTextDisplay(countryLang.countryRegion, countryTxt);
		assertEquals(txtDisplay, true);
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
