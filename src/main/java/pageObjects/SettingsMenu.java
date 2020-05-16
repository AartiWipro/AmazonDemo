package pageObjects;

import java.util.List;

import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

/**
 * The Settings Menu class contains all the Xpath and method related to settings menu.
 * @author Aarti
 *
 */
public class SettingsMenu  {

	// Concatenate driver
	public SettingsMenu(AndroidDriver<AndroidElement> driver) {		
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(xpath = "//*[@text ='Country & Language']")
	public MobileElement countryLanguage;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text ='Settings']")
	public MobileElement settingsMenu;
	@AndroidFindBy(id = "com.amazon.mShop.android.shopping:id/drawer_item_title")
	public static List <MobileElement> settingMenuOptions;
	
	
	/**
	 * The method will verify the menu options and will click on the selected option
	 * @param text : will define string value
	 */
	public static void selectSettingMenuOption(String text) {
		int count = settingMenuOptions.size();
		for(int i=0; i<= count; i++) {
			String textVal= settingMenuOptions.get(i).getText();
			if(textVal.equalsIgnoreCase(text)) {
				settingMenuOptions.get(i).click();
				break;
			}			
		}
	}
}