package pageObjects;

import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

/**
 * The Welcome page class contains all the Xpath and method related to welcome page.
 * @author Aarti
 */
public class WelcomePage {

	public AndroidDriver<AndroidElement> driver;
	// Concatenate driver
	public WelcomePage(AndroidDriver<AndroidElement> driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(id = "com.amazon.mShop.android.shopping:id/skip_sign_in_button")
	public MobileElement skipSignIn;
	@AndroidFindBy(id = "com.amazon.mShop.android.shopping:id/sign_in_button")
	public MobileElement signIn;
	@AndroidFindBy(accessibility = "Welcome")
	public MobileElement welcomePage;
}
