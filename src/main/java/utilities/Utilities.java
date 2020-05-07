package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

/**
 * The Utilities class contains all the common methods related to web pages
 * @author Aarti
 *
 */
public class Utilities {

	static AndroidDriver<AndroidElement> driver;

	/**
	 * @param driver : will define WebElement value
	 */
	public Utilities(AndroidDriver<AndroidElement> driver) {
		Utilities.driver = driver;
	}

	/**
	 * The ScrollToText method is use for performing scroll operation if element will be not visible
	 * @param Country : will define string value
	 * @return
	 */
	public WebElement ScrollToText(String Country) {
		WebElement ele = driver.findElementByAndroidUIAutomator(
				"new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + Country + "\"));");
		return ele;
	}

	/**
	 * The click method is use for performing click operation for web elements
	 * @param ele : will define WebElement value
	 * @throws InterruptedException
	 */
	public static void Click(WebElement ele) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(ele));
		ele.click();
	}

	/**
	 * @param value : will define string value
	 * @throws IOException
	 */
	public static void GetPropertiesValue(String value) throws IOException {
		FileInputStream fs = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\globle.properties");
		Properties prop = new Properties();
		prop.load(fs);
		prop.get(value);
	}
}
