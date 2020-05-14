package assertions;

import org.openqa.selenium.WebElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

/**
 * The Compare class implements all the method related to assertion and validation
 * @author Aarti
 *
 */

public class Validation {
	public static AndroidDriver<AndroidElement> driver;

	public Validation(AndroidDriver<AndroidElement> driver) {
		Validation.driver = driver;
	}

	/**
	 * The method is used for validating the WebElement which is displaying on web page 
	 * @param ele : will define WebElement value
	 * @return : will return the result in boolean
	 */
	public static boolean isElementDisplay(WebElement ele) {
		boolean result = false;
		result = ele.isDisplayed() ? true : false;
		return result;
	}

	/**
	 * The method is used for validating the WebElement text which is displaying on web page 
	 * @param ele : will define WebElement value
	 * @param text : will define string value
	 * @return : will return the result in boolean
	 * @throws InterruptedException 
	 */
	public static boolean isTextDisplay(WebElement ele, String text) throws InterruptedException {
		boolean result = false;		
		try {
			result = ele.getText().contains(text) ? true : false;
		} catch (Exception e) {
			Thread.sleep(3000);
			result = ele.getText().contains(text) ? true : false;
			e.printStackTrace();
		}				
		return result;
	}
	
	/**
	 * The method is used for validating the WebElement which is displaying on web page 
	 * @param ele : will define WebElement value
	 * @return : will return the result in boolean
	 */
	public static boolean isElementSelected(WebElement ele) {
		boolean result = false;
		result = ele.isSelected() ? true : false;
		return result;
	}
}
