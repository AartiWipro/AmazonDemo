package testCase;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.ResourceBundle;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import assertions.Validation;
import base.TestBase;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.SettingsMenu;
import pageObjects.CountryRegionLanguagePage;
import pageObjects.HamburgerMainMenu;
import pageObjects.WelcomePage;
import utilities.Utilities;

/**
 * The VerifyCountryRegion class is used for setting Country/region as Australia
 * 
 * @author Aarti
 *
 */
public class VerifyCountryRegion extends TestBase {

	public VerifyCountryRegion() throws IOException {
		super();
	}

	WebDriverWait wait;
	ResourceBundle global;
	WelcomePage web;
	HomePage home;
	HamburgerMainMenu mainMenu;
	Utilities util;
	SettingsMenu setMenu;
	CountryRegionLanguagePage countryLang;
	LoginPage login;
	ResourceBundle config;

	/**
	 * The method is use for starting the server, driver and initializing the object
	 * for all classes
	 * 
	 * @throws InterruptedException
	 * @throws IOException
	 */
	@BeforeTest
	public void setUp() throws InterruptedException, IOException {
		initialization();
		wait = new WebDriverWait(driver, 30);
		global = ResourceBundle.getBundle("global");
		web = new WelcomePage(driver);
		home = new HomePage(driver);
		mainMenu = new HamburgerMainMenu(driver);
		setMenu = new SettingsMenu(driver);
		countryLang = new CountryRegionLanguagePage(driver);
		login = new LoginPage(driver);
		config = ResourceBundle.getBundle("config");
	}

	/**
	 * The method is for existing user login which having account already
	 * 
	 * @throws InterruptedException
	 * @throws IOException
	 */
	@Test(priority = 0)
	public void loginTest() throws InterruptedException, IOException {
		logger.info("Welcome Page will display");
		wait.until(ExpectedConditions.visibilityOf(web.signIn));		
		boolean signInDisplay = Validation.isElementDisplay(web.signIn);
		assertEquals(signInDisplay, true);
		Utilities.Click(web.signIn);
		login.userlogin(config.getString("userEmail"), config.getString("userPassword"), driver);	
	}
	
	/**
	 * The test case will select Country/region as Australia after changing the
	 * default Country setting.
	 * 
	 * @throws InterruptedException
	 * @throws IOException
	 */
	@Test(dependsOnMethods = {"loginTest"})
	public void selectCountry() throws InterruptedException, IOException {		
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
		boolean settingDisplay = Utilities.verifyingPage(setMenu.settingsMenu, global.getString("settingsMenuTitle"));
		assertEquals(settingDisplay, true);
		SettingsMenu.selectSettingMenuOption(global.getString("selectedSettingsMenuOption"));
		logger.info("Country/Region & Language page will display");
		boolean ConLangDisplay = Utilities.verifyingPage(countryLang.countryRegionLang,
				global.getString("conRegLangTitle"));
		assertEquals(ConLangDisplay, true);
		CountryRegionLanguagePage.selectConLanButton(global.getString("selectedConLanButton"));
		logger.info("Country options will display with selected language");
		boolean conViewDisplay = Utilities.verifyingPage(countryLang.countryRegionView, global.getString("conRegView"));
		assertEquals(conViewDisplay, true);
	}

	/**
	 * The test case will verify Country/region as Australia
	 * 
	 * @throws InterruptedException
	 * @throws IOException
	 */
	@Test(dependsOnMethods = {"selectCountry"})
	public void verifyingCountry() throws InterruptedException, IOException {
		CountryRegionLanguagePage.selectCountry(global.getString("country"), driver);
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
	public void tearDown() throws InterruptedException, IOException {
		endSession();
	}
}
