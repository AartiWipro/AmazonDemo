package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

/**
 * The Settings Menu class contains all the Xpath and method related to settings menu.
 * @author Aarti
 *
 */
public class SettingsMenuPage  {

	// Concatenate driver
	public SettingsMenuPage(AndroidDriver<AndroidElement> driver) {		
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(xpath = "//*[@text ='Country & Language']")
	public WebElement countryLanguage;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text ='Settings']")
	public WebElement settingsMenu;
	
	/**
	 * @param ele : will define WebElement value
	 * @param text : will define string value
	 * @return  : will return the result in boolean.
	 */
	public static boolean verifyingSettingsMenu(WebElement ele, String text) {
		boolean result = false;		
		result = ele.getText().equals(text) ? true : false;				
		return result;
	}
}