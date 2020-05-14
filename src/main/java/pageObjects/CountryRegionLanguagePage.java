package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import utilities.Utilities;

/**
 * The CountryRegionLanguagePage class contains all the Xpath and method related
 * to CountryRegionLanguage Menu.
 * 
 * @author Aarti
 *
 */
public class CountryRegionLanguagePage {

	public CountryRegionLanguagePage(AndroidDriver<AndroidElement> driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(xpath = "//android.widget.Button[contains(@text, 'Country/Region:')]")
	public WebElement countryRegion;
	@AndroidFindBy(xpath = "//android.view.View[@text ='Country/Region & Language']")
	public WebElement countryRegionLang;
	@AndroidFindBy(xpath = "//android.view.View[@text ='Country/Region']")
	public WebElement countryRegionView;
	@AndroidFindBy(className = "android.widget.Button")
	public static List<WebElement> countryRegionBtn;
	@AndroidFindBy(className = "android.widget.RadioButton")
	public static List<WebElement> country;

	/**
	 * The Method will verify the text of the element
	 * 
	 * @param driver : will define WebElement value
	 * @return : will return the result in string.
	 * @throws InterruptedException
	 */
	public String getCountryNameText(AppiumDriver<AndroidElement> driver) throws InterruptedException {
		Thread.sleep(3000);
		String conRegValue = countryRegion.getText();
		return conRegValue;
	}

	/**
	 * The method will verify the menu options and will click on the selected option
	 * 
	 * @param text : will define string value
	 */
	public static void selectConLanButton(String text) {
		int count = countryRegionBtn.size();
		for (int i = 0; i <= count; i++) {
			String textVal = countryRegionBtn.get(i).getText();
			if (textVal.contains(text)) {
				countryRegionBtn.get(i).click();
				break;
			} 
		}
	}

	/**
	 * The method will verify the radio button and will click on if it is not
	 * selected
	 * 
	 * @param text : will define string value
	 * @throws InterruptedException
	 */
	public static void selectCountry(String countryName, AppiumDriver<AndroidElement> driver)
			throws InterruptedException {
		int count = country.size();
		try {
			for (int i = 0; i <= count; i++) {
				String conText = country.get(i).getText();
				if (conText.contains(countryName) && !country.get(i).isSelected()) {
					country.get(i).click();
					break;
				} else
					continue;
			}
		} catch (Exception e) {
			AndroidElement redioBtn = Utilities.scrollToElement(
					By.xpath("//android.widget.RadioButton[contains(@text, '" + countryName + "')]"), driver);
			if (!redioBtn.isSelected()) {
				redioBtn.click();
				e.printStackTrace();
			}
		}
	}
}