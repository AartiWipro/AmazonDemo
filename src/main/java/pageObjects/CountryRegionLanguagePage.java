package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

/**
 * The CountryRegionLanguagePage class contains all the Xpath and method related to CountryRegionLanguage Menu.
 * @author Aarti
 *
 */
public class CountryRegionLanguagePage  {

	// Concatenate driver
	public CountryRegionLanguagePage(AndroidDriver<AndroidElement> driver) {		
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(xpath = "//android.widget.Button[contains(@text, 'Country/Region:')]")
	public WebElement countryRegion;
	@AndroidFindBy(xpath = "//android.view.View[@text ='Country/Region & Language']")
	public WebElement countryRegionLang;
	@AndroidFindBy(xpath = "//android.view.View[@text ='Country/Region']")
	public WebElement countryRegionView;
	
	/**
	 * @param ele : will define WebElement value
	 * @param text : will define string value
	 * @return  : will return the result in boolean.
	 */
	public static boolean verifyingCountryRegionLang(WebElement ele, String text) {
		boolean result = false;		
		result = ele.getText().equals(text) ? true : false;				
		return result;
	}
	
	/**
	 * @param driver : will define WebElement value
	 * @return : will return the result in string.
	 * @throws InterruptedException 
	 */
	public String getCountryNameText(AppiumDriver<AndroidElement> driver ) throws InterruptedException {
		Thread.sleep(3000);
		String conRegValue = countryRegion.getText();
		return conRegValue;
	}

}