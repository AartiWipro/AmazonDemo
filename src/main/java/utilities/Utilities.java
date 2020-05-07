package utilities;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;

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
	 * The Method is will scroll the page until element will display
	 * @param by 
	 * @param driver : will define WebDriver value
	 * @return : it will return the webelement value
	 * @throws InterruptedException 
	 */
	public static AndroidElement scrollToElement(By by, AppiumDriver<AndroidElement> driver) throws InterruptedException {
		Thread.sleep(3000);
		AndroidElement element = null;
		int numberOfTimes = 10;
		Dimension size = driver.manage().window().getSize();
		int anchor = (int)(size.width / 2);
		//Swipe up to scroll down
		int startPoint = (int)(size.height - 10);
		int endPoint = 10;

		for (int i = 0; i < numberOfTimes; i++) {
		try {
		new TouchAction(driver)
		.longPress(PointOption.point(anchor, startPoint)) //.press(point(anchor, startPoint)) if used press need proper waiting time
		//.waitAction(waitOptions(ofMillis(miliseconds)))
		.moveTo(PointOption.point(anchor, endPoint)).release().perform();
		element = (AndroidElement) driver.findElement(by);
		i = numberOfTimes;
		} catch (NoSuchElementException ex) {
		System.out.println(String.format("Element not available. Scrolling times…", i + 1));
		}
		}
		return element;
		}
}
