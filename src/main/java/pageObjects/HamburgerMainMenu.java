package pageObjects;

import java.util.List;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.MobileElement;
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
	public MobileElement setting;
	@AndroidFindBy(xpath = "//*[@text ='Australia Amazon.com.au']")
	public MobileElement ausRedioBtn;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text ='Hello. Sign In']")
	public MobileElement hamburgerMenu;
	@AndroidFindBy(id = "com.amazon.mShop.android.shopping:id/drawer_item_title")
	public static List <MobileElement> mainMenuOptions;	
	
	/**
	 * The method will verify the menu options and will click on the selected option
	 * @param text : will define string value
	 */
	public static void selectMainMenuOption(String text) {
		int count = mainMenuOptions.size();
		for(int i=0; i<= count; i++) {
			String textVal= mainMenuOptions.get(i).getText();
			if(textVal.equalsIgnoreCase(text)) {
				mainMenuOptions.get(i).click();
				break;
			}				
		}
	}
}