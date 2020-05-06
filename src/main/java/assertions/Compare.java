package assertions;

import org.openqa.selenium.WebElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

/**
 * The Compare class implements all the method related to assertion and validation
 * @author Aarti
 *
 */
public class Compare {
	static AndroidDriver<AndroidElement> driver;

	public Compare(AndroidDriver<AndroidElement> driver) {
		Compare.driver = driver;
	}

	/**
	 * The validatePageURL method is used for validating the page URL with actual value
	 * @param ele :  will define WebElement value
	 * @param expectedURL : will define String value
	 * @return : will return the result in boolean
	 */
	public boolean validatePageURL(WebElement ele, String expectedURL) {
		boolean result = false;
		if (driver.getCurrentUrl().equalsIgnoreCase(expectedURL)) {
			result = true;
		}
		return result;
	}

	/**
	 * The validateElement method is used for validating the WebElement which is displaying on web page 
	 * @param ele : will define WebElement value
	 * @return : will return the result in boolean
	 */
	public static boolean validateElement(WebElement ele) {
		boolean result = false;
		result = ele.isDisplayed() ? true : false;
		return result;
	}

	/**
	 * The validateText method is used for validating the WebElement text which is displaying on web page 
	 * @param ele : will define WebElement value
	 * @param text : will define string value
	 * @return : will return the result in boolean
	 */
	public static boolean validateText(WebElement ele, String text) {
		boolean result = false;		
		result = ele.getText().equals(text) ? true : false;				
		return result;
	}

	/**
	 * The assertEquals method is used for validating two string values
	 * @param exp : will define string value
	 * @param act : will define string value
	 * @return : will return the result in boolean
	 */
	public static boolean assertEquals(String exp, String act) {
		boolean result = false;
		result = exp.contains(act) ? true : false;			
		return result;
	}

	/**
	 * The assertFail method is used for checking fail status
	 * The generateReport method is used for generating the Extent report
	 * @return : will return the result in boolean
	 */
	public static boolean assertFail() {
		return false;
	}
}
