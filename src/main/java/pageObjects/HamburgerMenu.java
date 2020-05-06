package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

/**
 * The HamburgerMenu class contains all the Xpath and method related to Hamburger sMenu.
 * @author Aarti
 *
 */
public class HamburgerMenu  {

	// Concatenate driver
	public HamburgerMenu(AndroidDriver<AndroidElement> driver) {		
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(xpath = "//*[@text ='Settings']")
	public WebElement setting;
	@AndroidFindBy(xpath = "//*[@text ='Country & Language']")
	public WebElement countryLanguage;
	@AndroidFindBy(xpath = "//android.widget.Button[contains(@text, 'Country/Region:')]")
	public WebElement countryRegion;
	@AndroidFindBy(xpath = "//*[@text ='Australia Amazon.com.au']")
	public WebElement ausRedioBtn;

	/**
	 * @param driver
	 * @return
	 */
	public String GetCountryNameText(AppiumDriver<AndroidElement> driver ) {
		String conRegValue = countryRegion.getText();
		return conRegValue;
	}
}