package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

/**
 * The HomePage class contains all the Xpath and method related to home page.
 * @author Aarti
 *
 */
public class HomePage {

	// Concatenate driver
	public HomePage(AndroidDriver<AndroidElement> driver) {	
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(accessibility = "Navigation panel, button, double tap to open side panel")
	public WebElement sidePanel;	
	@AndroidFindBy(accessibility = "Home")
	public WebElement homePage;
	@AndroidFindBy(xpath = "//android.widget.EditText[@text ='Search']")
	public WebElement homePageSearch;
	
	/**
	 * @param ele : will define WebElement value
	 * @param text : will define string value
	 * @return  : will return the result in boolean.
	 */
	public static boolean verifyingHomePage(WebElement ele, String text) {
		boolean result = false;		
		result = ele.getText().equals(text) ? true : false;				
		return result;
	}
}