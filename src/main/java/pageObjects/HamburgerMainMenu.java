package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

/**
 * The HamburgerMenu class contains all the Xpath and method related to Hamburger Menu.
 * @author Aarti
 *
 */
public class HamburgerMainMenu  {

	// Concatenate driver
	public HamburgerMainMenu(AndroidDriver<AndroidElement> driver) {		
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(xpath = "//*[@text ='Settings']")
	public WebElement setting;
	@AndroidFindBy(xpath = "//*[@text ='Australia Amazon.com.au']")
	public WebElement ausRedioBtn;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text ='Hello. Sign In']")
	public WebElement hamburgerMenu;
	
	/**
	 * @param ele : will define WebElement value
	 * @param text : will define string value
	 * @return  : will return the result in boolean.
	 */
	public static boolean verifyingHamburgerMenu(WebElement ele, String text) {
		boolean result = false;		
		result = ele.getText().equals(text) ? true : false;				
		return result;
	}
}