package pageObjects;

import static org.testng.Assert.assertEquals;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import assertions.Validation;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

/**
 * The HomePage class contains all the Xpath and method related to home page.
 * 
 * @author Aarti
 *
 */
public class LoginPage {

	WebDriverWait wait;

	// Concatenate driver
	public LoginPage(AndroidDriver<AndroidElement> driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		wait = new WebDriverWait(driver, 30);
	}

	@AndroidFindBy(xpath = "//android.view.View[@text ='Login. Already a customer?']")
	public WebElement loginRedio;
	@AndroidFindBy(id = "//android.widget.EditText[@id='ap_email_login']")
	public WebElement emailVal;
	@AndroidFindBy(xpath = "//android.widget.Button[@id='continue']")
	public WebElement continueBtn;
	@AndroidFindBy(xpath = "//android.widget.EditText[@id='ap_password']")
	public WebElement password;
	@AndroidFindBy(xpath = "//android.view.View[@text ='Login']")
	public WebElement loginTitle;

	/**
	 * The method is for existing user login which having account already
	 * 
	 * @param text : will define string value
	 * @throws InterruptedException
	 */
	public void userlogin(String email, String pass, AndroidDriver<AndroidElement> driver)
			throws InterruptedException {
		boolean eleSelected = Validation.isElementDisplay(loginRedio);
		assertEquals(eleSelected, true);
		wait.until(ExpectedConditions.visibilityOf(emailVal));
		emailVal.sendKeys(email);
		continueBtn.click();
		wait.until(ExpectedConditions.visibilityOf(loginTitle));
		emailVal.sendKeys(pass);		
	}
}